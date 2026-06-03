# 🏨 Hotel Booking API

Sistema de reservas de hotel com controle de disponibilidade e conflito de datas.

## 📋 Sobre o Projeto

API para gerenciar reservas de um hotel. Controla quartos por tipo (SINGLE, DOUBLE, SUITE), verifica automaticamente se o quarto está disponível no período solicitado e evita double-booking.

## ✨ Funcionalidades

- ✅ Cadastrar quartos por tipo e preço por diária
- ✅ Verificar disponibilidade por datas
- ✅ Criar reserva com validação de conflito de datas
- ✅ Confirmar, cancelar ou completar reserva
- ✅ Cálculo automático do valor total da estadia
- ✅ Listar reservas por quarto
- ✅ Listar reservas por hóspede

## 🔗 Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET/POST | `/api/rooms` | Listar / Cadastrar quartos |
| GET | `/api/rooms/available` | Quartos disponíveis |
| GET/POST | `/api/bookings` | Listar / Criar reserva |
| PATCH | `/api/bookings/{id}/status` | Atualizar status |
| GET | `/api/bookings/guest/{id}` | Reservas por hóspede |

## 🛠️ Tecnologias

- Java 17 · Spring Boot 3.2 · MySQL · Maven · Lombok
