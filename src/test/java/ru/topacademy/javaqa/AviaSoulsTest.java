package ru.topacademy.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {
    AviaSouls aviaSouls = new AviaSouls();
    TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();
    TicketTimeComparator comparator = new TicketTimeComparator();

    Ticket ticket1 = new Ticket("Москва", "Сочи", 50000, 10_00, 20_00);
    Ticket ticket2 = new Ticket("Екатеринбург", "Москва", 30000, 11_00, 21_00);
    Ticket ticket3 = new Ticket("Москва", "Новосибирск", 25000, 12_00, 22_00);


    // Тест на добавление билетов и получение всех билетов
    @Test
    public void shouldAddAndFindAllTickets() {
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] expected = {ticket1, ticket2, ticket3};
        Ticket[] actual = aviaSouls.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Тест на обработку ситуации, когда билеты не найдены
    @Test
    public void shouldHandleNoTicketsFound() {
        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("Москва", "Лос-Анджелес");

        Assertions.assertArrayEquals(expected, actual);
    }

    // Тест на сравнение билетов,что первый билет дороже второго.
    @Test
    public void shouldReturnTrueWhenFirstTicketIsMoreExpensiveThanSecond() {
        Assertions.assertTrue(ticket1.compareTo(ticket2) > 0);
    }

    // Тест на сравнение билетов,что второй билет дороже первого.
    @Test
    public void shouldReturnTrueWhenSecondTicketIsMoreExpensiveThanFirst() {
        Assertions.assertTrue(ticket2.compareTo(ticket1) < 0);
    }

    //Время полета обоих билетов одинаково.Ожидается нулевой результат.
    @Test
    public void shouldReturnZeroWhenTicketsHaveEqualFlightTimes() {

        Ticket ticket1 = new Ticket("Москва", "Сочи", 50000, 10_00, 20_00); // 10 часов полета
        Ticket ticket2 = new Ticket("Москва", "Сочи", 50000, 10_00, 20_00); // 10 часов полета

        int result = comparator.compare(ticket1, ticket2);

        Assertions.assertEquals(0, result, "Результат равен 0, так как время полета одинаково.");
    }

    //Тест на сравнение времени полета, когда первый билет имеет более длительный полет, чем второй
    @Test
    public void shouldReturnPositiveWhenFirstTicketHasLongerFlightTimeThanSecond() {
        Ticket ticket1 = new Ticket("Москва", "Сочи", 50000, 10_00, 20_00); // 10 часов полета
        Ticket ticket2 = new Ticket("Москва", "Сочи", 30000, 11_00, 19_00); // 8 часов полета

        int result = comparator.compare(ticket1, ticket2);

        Assertions.assertTrue(result > 0, "Результат должен быть положительным, так как у первого билета время полета больше.");
    }

    // Тест на сравнение времени полета, когда второй билет имеет более длительный полет, чем первый
    @Test
    public void shouldReturnNegativeWhenSecondTicketHasLongerFlightTimeThanFirst() {
        Ticket ticket1 = new Ticket("Москва", "Сочи", 30000, 11_00, 19_00); // 8 часов полета
        Ticket ticket2 = new Ticket("Москва", "Сочи", 50000, 10_00, 20_00); // 10 часов полета

        int result = comparator.compare(ticket1, ticket2);

        Assertions.assertTrue(result < 0, "Результат должен быть отрицательным, так как у второго билета время полета больше.");
    }

    // Тест на поиск и сортировку билетов по времени полёта
    @Test
    public void shouldSearchAndSortTicketsByTime() {

        Ticket ticket1 = new Ticket("Москва", "Сочи", 50000, 10, 20);
        Ticket ticket2 = new Ticket("Москва", "Сочи", 30000, 11, 21);
        Ticket ticket3 = new Ticket("Москва", "Новосибирск", 25000, 12, 22);
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = aviaSouls.searchAndSortBy("Москва", "Сочи", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    //возвращаемая цена соответствует ожидаемой
    @Test
    public void shouldReturnCorrectPrice() {
        Ticket ticket = new Ticket("Екатеринбург", "Москва", 30000, 10_00, 15_00);

        int price = ticket.getPrice();

        Assertions.assertEquals(30000, price);
    }

    // Тест на сравнение билетов по цене
    @Test
    public void shouldCompareTicketsByPrice() {
        Ticket ticket1 = new Ticket("SVO", "JFK", 50000, 10, 20);
        Ticket ticket2 = new Ticket("SVO", "JFK", 30000, 11, 21);

        Assertions.assertTrue(ticket1.compareTo(ticket2) > 0);
        Assertions.assertTrue(ticket2.compareTo(ticket1) < 0);
    }

    @Test
    public void testTicketTimeComporator() {
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Assertions.assertEquals(1,ticket1.compareTo(ticket2));

    }

    @Test
    public void test2TicketTimeComporator() {
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Assertions.assertEquals(-1,ticket2.compareTo(ticket1));

    }
    // Тест на проверку, что метод search возвращает массив билетов, отсортированных по цене
    @Test
    public void shouldReturnSortedTicketsByPriceForGivenDepartureAndArrival() {
        AviaSouls aviaSouls = new AviaSouls();
        Ticket ticket1 = new Ticket("Москва", "Сочи", 45000, 10_00, 20_00);
        Ticket ticket2 = new Ticket("Москва", "Сочи", 30000, 11_00, 21_00);
        Ticket ticket3 = new Ticket("Москва", "Сочи", 35000, 12_00, 22_00);
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);

        Ticket[] expected = {ticket2, ticket3, ticket1};
        Ticket[] actual = aviaSouls.search("Москва", "Сочи");

        Assertions.assertArrayEquals(expected, actual, "Билеты должны быть отсортированы по цене.");
    }
    
}
