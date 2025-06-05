/**
 * horarios.js
 * Módulo que armazena e fornece horários de ônibus.
 * Autor: Rafael Passos Domingues
 * Última atualização: 05/06/2025
 */

const horariosOnibus = {
  'Terminal-JardimAlvorada': {
    origem: 'Terminal Urbano',
    destino: 'Jardim Alvorada',
    horarios: [
      '6:15', '7:15', '8:15', '9:15', '10:15', '11:15',
      '12:15', '13:15', '14:15', '15:15', '16:15', '17:15',
      '18:15', '19:15', '20:15', '21:15', '22:15', '23:00'
    ]
  },
  'JardimAlvorada-Terminal': {
    origem: 'Jardim Alvorada',
    destino: 'Terminal Urbano',
    horarios: [
      '6:30', '7:30', '8:30', '9:30', '10:30', '11:30',
      '12:30', '13:30', '14:30', '15:30', '16:30', '17:30',
      '18:30', '19:30', '20:30', '21:30', '22:30'
    ]
  },
  'Terminal-Campus': {
    origem: 'Terminal Urbano',
    destino: 'Campus',
    horarios: [
      '6:45', '7:45', '8:45', '9:45', '10:45', '11:45',
      '12:45', '13:45', '14:45', '15:45', '16:45', '17:45',
      '18:45', '19:45', '20:45', '21:45'
    ]
  },
  'Campus-Terminal': {
    origem: 'Campus',
    destino: 'Terminal Urbano',
    horarios: [
      '7:05', '8:05', '9:05', '10:05', '11:05', '12:05',
      '13:05', '14:05', '15:05', '16:05', '17:05', '18:05',
      '19:05', '20:05', '21:05', '22:05'
    ]
  },
  'Terminal-Pinheirinho': {
    origem: 'Terminal Urbano',
    destino: 'Pinheirinho',
    horarios: [
      '4:50', '5:15', '6:15', '7:15', '8:15', '9:15', '10:15',
      '11:15', '12:15', '13:15', '14:15', '15:15', '16:15',
      '17:15', '18:15', '19:15', '21:15', '22:15'
    ]
  }
  // ... adicione outras linhas se necessário!
};

export { horariosOnibus };
