# Hotel Booking API

Sistema de reservas de hotel com verificação automática de conflito de datas, cálculo de valor total da estadia e gestão de quartos por tipo e disponibilidade.

## Tecnologias
Java 17 · Spring Boot 3.2 · Spring Data JPA · MySQL · Maven · Swagger/OpenAPI

## Funcionalidades
- Cadastro de quartos por tipo (Standard, Deluxe, Suite, Presidential)
- Reserva com verificação de conflito de datas via JPQL
- Cálculo automático do valor total (diárias × preço/noite)
- Consulta de reservas por hóspede
- Cancelamento de reserva

## Como Executar
```bash
mvn spring-boot:run
# Acesse: http://localhost:8093/swagger-ui.html
```
**Patryck Martins Langsdorff** — Java Back End Developer Junior | [LinkedIn](https://www.linkedin.com/in/patryck-martins-langsdorff)
