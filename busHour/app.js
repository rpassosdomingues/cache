import { buscarHorarios, listarLinhas, buscarOrigemDestino } from './api.js';
import { buscarPorCEP } from './api.js';

// Elementos da interface
const getLocationBtn = document.getElementById('getLocationBtn');
const cepInput = document.getElementById('cepInput');
const searchCepBtn = document.getElementById('searchCepBtn');
const mapContainer = document.getElementById('map');
const confirmLocationBtn = document.getElementById('confirmLocationBtn');
const resultsContainer = document.getElementById('resultsContainer');
const busResults = document.getElementById('busResults');
const lastUpdate = document.getElementById('lastUpdate');

// Configuração do mapa
let map;
let userMarker;
let busStopsLayer;
let selectedLocation = null;

// Atualiza a data no footer
lastUpdate.textContent = new Date().toLocaleDateString('pt-BR');

// Inicializa o aplicativo
initApp();

function initApp() {
    // Inicializa o mapa
    initMap();
    
    // Event listeners
    getLocationBtn.addEventListener('click', getUserLocation);
    searchCepBtn.addEventListener('click', searchByCEP);
    confirmLocationBtn.addEventListener('click', confirmLocation);
}

function initMap() {
    // Mapa centrado na cidade (coordenadas aproximadas)
    map = L.map('map').setView([-25.4284, -49.2733], 13);
    
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
    
    // Permite ao usuário clicar no mapa para ajustar a localização
    map.on('click', (e) => {
        selectedLocation = e.latlng;
        updateUserMarker(selectedLocation);
        confirmLocationBtn.classList.remove('hidden');
    });
}

async function getUserLocation() {
    if (!navigator.geolocation) {
        alert('Geolocalização não é suportada pelo seu navegador.');
        return;
    }
    
    getLocationBtn.disabled = true;
    getLocationBtn.textContent = 'Buscando localização...';
    
    try {
        const position = await new Promise((resolve, reject) => {
            navigator.geolocation.getCurrentPosition(resolve, reject);
        });
        
        const { latitude, longitude } = position.coords;
        selectedLocation = L.latLng(latitude, longitude);
        
        updateUserMarker(selectedLocation);
        map.setView(selectedLocation, 16);
        confirmLocationBtn.classList.remove('hidden');
        
    } catch (error) {
        console.error('Erro ao obter localização:', error);
        alert('Não foi possível obter sua localização. Por favor, tente novamente ou insira seu CEP manualmente.');
    } finally {
        getLocationBtn.disabled = false;
        getLocationBtn.innerHTML = '<span class="icon">📍</span> Usar minha localização';
    }
}

async function searchByCEP() {
    const cep = cepInput.value.trim().replace(/\D/g, '');
    
    if (cep.length !== 8) {
        alert('Por favor, insira um CEP válido com 8 dígitos.');
        return;
    }
    
    searchCepBtn.disabled = true;
    searchCepBtn.textContent = 'Buscando...';
    
    try {
        const location = await buscarPorCEP(cep);
        
        if (location) {
            selectedLocation = L.latLng(location.lat, location.lng);
            updateUserMarker(selectedLocation);
            map.setView(selectedLocation, 16);
            confirmLocationBtn.classList.remove('hidden');
        } else {
            alert('CEP não encontrado. Por favor, verifique o CEP digitado.');
        }
    } catch (error) {
        console.error('Erro ao buscar CEP:', error);
        alert('Ocorreu um erro ao buscar o CEP. Por favor, tente novamente.');
    } finally {
        searchCepBtn.disabled = false;
        searchCepBtn.textContent = 'Buscar';
    }
}

function updateUserMarker(location) {
    if (userMarker) {
        map.removeLayer(userMarker);
    }
    
    userMarker = L.marker(location, {
        icon: L.divIcon({
            className: 'user-marker',
            html: '📍',
            iconSize: [30, 30]
        }),
        zIndexOffset: 1000
    }).addTo(map);
    
    userMarker.bindPopup('Sua localização').openPopup();
}

async function confirmLocation() {
    if (!selectedLocation) return;
    
    confirmLocationBtn.disabled = true;
    confirmLocationBtn.textContent = 'Buscando paradas...';
    
    try {
        // Simula busca das paradas próximas (na implementação real, isso viria de uma API)
        const nearbyStops = await findNearbyBusStops(selectedLocation);
        
        // Mostra as paradas no mapa
        showBusStopsOnMap(nearbyStops);
        
        // Busca os horários para cada linha que passa nas paradas próximas
        const busLinesInfo = await getBusLinesInfo(nearbyStops);
        
        // Exibe os resultados
        displayBusResults(busLinesInfo);
        
        resultsContainer.classList.remove('hidden');
        
    } catch (error) {
        console.error('Erro ao buscar paradas:', error);
        alert('Ocorreu um erro ao buscar as paradas de ônibus próximas.');
    } finally {
        confirmLocationBtn.disabled = false;
        confirmLocationBtn.textContent = 'Confirmar localização';
    }
}

function showBusStopsOnMap(stops) {
    // Remove paradas anteriores se existirem
    if (busStopsLayer) {
        map.removeLayer(busStopsLayer);
    }
    
    const markers = stops.map(stop => {
        return L.marker([stop.lat, stop.lng], {
            icon: L.divIcon({
                className: 'bus-stop-marker',
                html: '🚏',
                iconSize: [25, 25]
            })
        }).bindPopup(`<b>${stop.name}</b><br>Linhas: ${stop.lines.join(', ')}`);
    });
    
    busStopsLayer = L.layerGroup(markers).addTo(map);
    
    // Ajusta o zoom para mostrar todas as paradas e o usuário
    const bounds = L.latLngBounds([
        selectedLocation,
        ...stops.map(stop => L.latLng(stop.lat, stop.lng))
    ]);
    map.fitBounds(bounds, { padding: [50, 50] });
}

function displayBusResults(busLinesInfo) {
    busResults.innerHTML = '';
    
    if (busLinesInfo.length === 0) {
        busResults.innerHTML = '<p class="no-results">Nenhum ônibus encontrado nas proximidades.</p>';
        return;
    }
    
    // Ordena por próximo horário
    busLinesInfo.sort((a, b) => {
        const nextTimeA = a.times[0];
        const nextTimeB = b.times[0];
        return nextTimeA.localeCompare(nextTimeB);
    });
    
    busLinesInfo.forEach(line => {
        const card = document.createElement('div');
        card.className = 'bus-card';
        
        const destinationInfo = buscarOrigemDestino(line.lineId);
        const destinationText = destinationInfo 
            ? `${destinationInfo.origem} → ${destinationInfo.destino}`
            : 'Informação de destino não disponível';
        
        card.innerHTML = `
            <h3>Linha ${line.lineId}</h3>
            <p>${destinationText}</p>
            <p>Próximo: <strong>${line.times[0]}</strong></p>
            <div class="bus-times">
                ${line.times.slice(0, 5).map(time => 
                    `<span class="time-badge">${time}</span>`
                ).join('')}
            </div>
        `;
        
        busResults.appendChild(card);
    });
}

// Funções de simulação (seriam substituídas por chamadas reais à API)
async function findNearbyBusStops(location) {
    // Simulação - na prática, isso viria de uma API com dados reais
    return [
        {
            id: 'stop1',
            name: 'Parada Terminal Urbano',
            lat: location.lat + 0.001,
            lng: location.lng + 0.001,
            lines: ['Terminal-JardimAlvorada', 'Terminal-Campus']
        },
        {
            id: 'stop2',
            name: 'Parada Rua Principal',
            lat: location.lat - 0.001,
            lng: location.lng + 0.002,
            lines: ['Terminal-JardimAlvorada', 'Terminal-Pinheirinho']
        }
    ];
}

async function getBusLinesInfo(stops) {
    const allLines = new Set();
    stops.forEach(stop => {
        stop.lines.forEach(line => allLines.add(line));
    });
    
    const now = new Date();
    const currentHour = now.getHours();
    const currentMinute = now.getMinutes();
    
    return Array.from(allLines).map(lineId => {
        const schedules = buscarHorarios(lineId) || [];
        
        // Filtra horários que ainda não passaram hoje
        const upcomingTimes = schedules.filter(time => {
            const [h, m] = time.split(':').map(Number);
            return h > currentHour || (h === currentHour && m >= currentMinute);
        });
        
        return {
            lineId,
            times: upcomingTimes.length > 0 ? upcomingTimes : ['Nenhum horário disponível']
        };
    });
}