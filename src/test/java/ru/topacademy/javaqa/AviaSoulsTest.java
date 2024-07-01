package ru.topacademy.javaqa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {

    // Тест на добавление билетов и получение всех билетов
    @Test
    void shouldAddAndFindAllTickets() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SVO", "JFK", 50000, 10, 20);
        Ticket ticket2 = new Ticket("SVO", "JFK", 30000, 11, 21);
        manager.add(ticket1);
        manager.add(ticket2);

        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Тест на поиск и сортировку билетов по цене
    @Test
    void shouldSearchAndSortTicketsByPrice() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SVO", "JFK", 50000, 10, 20);
        Ticket ticket2 = new Ticket("SVO", "JFK", 30000, 11, 21);
        Ticket ticket3 = new Ticket("SVO", "DXB", 25000, 12, 22);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] expected = {ticket2, ticket1};
        Ticket[] actual = manager.search("SVO", "JFK");

        Assertions.assertArrayEquals(expected, actual);
    }


    // Тест на обработку ситуации, когда билеты не найдены
    @Test
    void shouldHandleNoTicketsFound() {
        AviaSouls manager = new AviaSouls();
        Ticket[] expected = {};
        Ticket[] actual = manager.search("SVO", "LAX");

        Assertions.assertArrayEquals(expected, actual);
    }

    // Тест на сравнение билетов по цене
    @Test
    void shouldCompareTicketsByPrice() {
        Ticket ticket1 = new Ticket("SVO", "JFK", 50000, 10, 20);
        Ticket ticket2 = new Ticket("SVO", "JFK", 30000, 11, 21);

        Assertions.assertTrue(ticket1.compareTo(ticket2) > 0);
        Assertions.assertTrue(ticket2.compareTo(ticket1) < 0);
    }

    // Тест на поиск и сортировку билетов по времени полёта
    @Test
    void shouldSearchAndSortTicketsByTime() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("SVO", "JFK", 50000, 10, 20);
        Ticket ticket2 = new Ticket("SVO", "JFK", 30000, 11, 21);
        Ticket ticket3 = new Ticket("SVO", "DXB", 25000, 12, 22);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Ticket[] expected = {ticket1, ticket2}; // Обратите внимание на порядок билетов
        Ticket[] actual = manager.searchAndSortBy("SVO", "JFK", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    // Тест на проверку, что метод compare возвращает отрицательное значение,
    // когда время полёта первого билета меньше времени полёта второго билета
    @Test
    void shouldReturnNegativeWhenFirstTicketHasShorterFlightTime() {
        Ticket ticket1 = new Ticket("SVO", "JFK", 30000, 10, 15); // 5 часов полёта
        Ticket ticket2 = new Ticket("SVO", "JFK", 50000, 10, 20); // 10 часов полёта
        TicketTimeComparator comparator = new TicketTimeComparator();

        int result = comparator.compare(ticket1, ticket2);

        Assertions.assertEquals(-1, result);
    }

    // Тест на проверку, что метод compare возвращает положительное значение,
    // когда время полёта первого билета больше времени полёта второго билета
    @Test
    void shouldReturnPositiveWhenFirstTicketHasLongerFlightTime() {
        Ticket ticket1 = new Ticket("SVO", "JFK", 50000, 10, 20); // 10 часов полёта
        Ticket ticket2 = new Ticket("SVO", "JFK", 30000, 10, 15); // 5 часов полёта
        TicketTimeComparator comparator = new TicketTimeComparator();

        int result = comparator.compare(ticket1, ticket2);

        Assertions.assertEquals(1, result);
    }

    // Тест на проверку, что метод compare возвращает ноль,
    // когда время полёта обоих билетов одинаково
    @Test
    void shouldReturnZeroWhenTicketsHaveSameFlightTime() {
        Ticket ticket1 = new Ticket("SVO", "JFK", 40000, 10, 17); // 7 часов полёта
        Ticket ticket2 = new Ticket("SVO", "JFK", 40000, 10, 17); // 7 часов полёта
        TicketTimeComparator comparator = new TicketTimeComparator();

        int result = comparator.compare(ticket1, ticket2);

        Assertions.assertEquals(0, result);
    }

    @Test
    void shouldReturnZeroWhenPricesAreEqual() {
        // Создание двух объектов билетов с одинаковой ценой
        Ticket ticket1 = new Ticket("SVO", "JFK", 30000, 10, 15);
        Ticket ticket2 = new Ticket("SVO", "JFK", 30000, 10, 15);

        // Сравнение билетов с помощью метода compareTo
        int comparisonResult = ticket1.compareTo(ticket2);

        // Проверка, что результат сравнения равен 0
        Assertions.assertEquals(0, comparisonResult);
    }

    @Test
    void shouldReturnCorrectPrice() {
        // Создание объекта билета с указанной ценой
        Ticket ticket = new Ticket("SVO", "JFK", 30000, 10, 15); // Цена 30000

        // Получение цены с помощью метода getPrice
        int price = ticket.getPrice();

        // Проверка, что возвращаемая цена соответствует ожидаемой
        Assertions.assertEquals(30000, price);
    }

    @Test
    void equalsShouldReturnTrueForSameObject() {
        // Создание объекта билета
        Ticket ticket = new Ticket("SVO", "JFK", 30000, 10, 15);

        // Проверка, что объект равен сам себе
        Assertions.assertTrue(ticket.equals(ticket));
    }

    @Test
    void equalsShouldReturnFalseForNull() {
        // Создание объекта билета
        Ticket ticket = new Ticket("SVO", "JFK", 30000, 10, 15);

        // Проверка, что объект не равен null
        Assertions.assertFalse(ticket.equals(null));
    }

    @Test
    void equalsShouldReturnFalseForDifferentClass() {
        // Создание объекта билета
        Ticket ticket = new Ticket("SVO", "JFK", 30000, 10, 15);

        // Создание объекта другого класса
        Object other = new Object();

        // Проверка, что объект билета не равен объекту другого класса
        Assertions.assertFalse(ticket.equals(other));
    }

    @Test
    void equalsShouldReturnTrueForEqualTickets() {
        // Создание двух одинаковых объектов билетов
        Ticket ticket1 = new Ticket("SVO", "JFK", 30000, 10, 15);
        Ticket ticket2 = new Ticket("SVO", "JFK", 30000, 10, 15);

        // Проверка, что билеты равны
        Assertions.assertTrue(ticket1.equals(ticket2));
    }

    @Test
    void hashCodeShouldBeConsistent() {
        // Создание объекта билета
        Ticket ticket = new Ticket("SVO", "JFK", 30000, 10, 15);

        // Проверка, что хеш-коды одинаковы при многократном вызове
        int expectedHashCode = ticket.hashCode();
        Assertions.assertEquals(expectedHashCode, ticket.hashCode());
        Assertions.assertEquals(expectedHashCode, ticket.hashCode());
    }

    @Test
    void equalTicketsShouldHaveSameHashCode() {
        // Создание двух одинаковых объектов билетов
        Ticket ticket1 = new Ticket("SVO", "JFK", 30000, 10, 15);
        Ticket ticket2 = new Ticket("SVO", "JFK", 30000, 10, 15);

        // Проверка, что у равных объектов одинаковые хеш-коды
        Assertions.assertEquals(ticket1.hashCode(), ticket2.hashCode());
    }
}
