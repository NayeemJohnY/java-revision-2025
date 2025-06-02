package tests.java8;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDateTimeAPI {

    // ### ✅ **Beginner (1–7)**

    @Test
    public void testDateTimeAPIBeginner() {
        // 1. Create a `LocalDate` object for your birthday and print the day of the
        // week.
        LocalDate localDate = LocalDate.of(1994, 3, 30);
        Assert.assertEquals(localDate.getDayOfWeek(), DayOfWeek.WEDNESDAY);

        // 2. Get the current date and add 10 days to it.
        localDate = LocalDate.now().plusDays(10);
        System.out.println(localDate.getDayOfMonth());

        // 3. Format a `LocalDate` in the format `dd-MM-yyyy`.
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        // 4. Parse the string `"2025-12-31"` into a `LocalDate`.
        Assert.assertEquals(localDate.parse("2025-12-31").getMonthValue(), 12);

        // 5. Print the number of days between today and New Year’s Day.
        System.out.println(localDate.now());
        long days = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(2026, 01, 01));
        System.out.println(days);

        // 6. Create a `LocalTime` of `14:30:15` and add 2 hours and 45 minutes.
        LocalTime localTime = LocalTime.of(14, 30, 15).plusHours(2).plusMinutes(45);
        System.out.println(localTime);

        // 7. Get the current date-time in `Asia/Kolkata` timezone.
        System.out.println(ZoneId.getAvailableZoneIds());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Kolkata"));
        System.out.println(localDateTime);
    }

    // ### 🟡 **Intermediate (8–14)**
    public long numberOfMonths(LocalDate date1, LocalDate date2) {
        return ChronoUnit.MONTHS.between(date1, date2);
    }

    public boolean isWeekEnd(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    @Test
    public void testDateTimeAPIIntermediate() {
        // 8. Write a method that takes two `LocalDate`s and returns the number of
        // months between them.
        Assert.assertEquals(numberOfMonths(LocalDate.now(), LocalDate.now().plusMonths(5)), 5);

        // 9. Create a recurring meeting on the last Friday of every month for the next
        // 6 months.
        // Solution 1
        LocalDate date = LocalDate.now();
        for (int i = 1; i <= 6; i++) {
            int lastDayOfMonth = date.lengthOfMonth();
            date = LocalDate.of(date.getYear(), date.getMonthValue(), lastDayOfMonth);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            int lastFridayDays = (dayOfWeek.getValue() - DayOfWeek.FRIDAY.getValue() + 7) % 7;
            LocalDate meetingDate = date.minusDays(lastFridayDays);
            System.out.println(meetingDate);
            date = date.plusMonths(1);
        }
        System.out.println("=============================");
        // Solution 2
        date = LocalDate.now();
        for (int i = 0; i < 6; i++) {
            int lastDayOfMonth = date.lengthOfMonth();
            date = LocalDate.of(date.getYear(), date.getMonthValue(), lastDayOfMonth);
            LocalDate meetingDate = date.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
            System.out.println(meetingDate);
            date = date.plusMonths(1);
        }
        System.out.println("=============================");
        // Solution 3
        date = LocalDate.now();
        for (int i = 0; i < 6; i++) {
            System.out.println(date.plusMonths(i).with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
        }
        System.out.println("============================");
        // 10. Convert a `LocalDateTime` to `Instant` using the system default timezone.
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(instant);

        // 11. Write a method to check if a given `LocalDate` is a weekend.
        System.out.println("Is Weekend: " + isWeekEnd(date));
        System.out.println("Is Weekend: " + isWeekEnd(date.plusDays(1)));

        // 12. Format a `ZonedDateTime` to ISO 8601 string.
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        String isoDateTimeString = zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("ZonedISOString: " + isoDateTimeString);

        // 13. Given a list of birthdates, return the count of people born in a leap
        // year
        List<LocalDate> birthdates = Arrays.asList(
                LocalDate.of(2024, 1, 1), LocalDate.of(2025, 12, 12),
                LocalDate.of(1994, 3, 30), LocalDate.now(), LocalDate.now().plusYears(3));
        System.out.println(birthdates.stream().filter(LocalDate::isLeapYear).count());

        // 14. Convert a `Date` (legacy) to `LocalDate`.
        Date legacyDate = new Date();
        System.out.println(legacyDate);
        System.out.println(LocalDate.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault()));
    }

    // ### 🔴 **Advanced (15–20)**
    public void calculateAgeInYearsMonthsDays(LocalDate birthDate) {
        LocalDate todaysDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(birthDate, todaysDate);
        long months = ChronoUnit.MONTHS.between(birthDate, todaysDate);
        long days = ChronoUnit.DAYS.between(birthDate, todaysDate);
        System.out.println("Age in Years: " + years);
        System.out.println("Age in Months: " + months);
        System.out.println("Age in Days: " + days);
    }

    public LocalDate nextBusinessDay(LocalDate date) {
        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY))
            return date.plusDays(3);
        else if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY))
            return date.plusDays(2);
        // return date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        return date.plusDays(1);
    }

    public void timeSlots(LocalTime from, LocalTime to){
        while(from.isBefore(to)){
            LocalTime endTime = from.plusMinutes(30);
            System.out.println(from.format(DateTimeFormatter.ofPattern("hh:mm a").withLocale(java.util.Locale.ENGLISH)) +
                 " - " + endTime.format(DateTimeFormatter.ofPattern("hh:mm a").withLocale(java.util.Locale.ENGLISH)));
            from = endTime;
        }
    }

    @Test
    public void testDateTimeAPIAdvanced() {
        // 15. Create a utility that calculates the age in years, months, and days from
        // a birthdate.
        calculateAgeInYearsMonthsDays(LocalDate.of(1994, 03, 30));

        // 16. Simulate a countdown timer using `Duration` and print remaining time
        // every second.
        Duration countDownDuration = Duration.ofSeconds(5);
        Duration oneSecondDuration = Duration.ofSeconds(1);
        while (!countDownDuration.isZero()) {
            try {
                Thread.sleep(oneSecondDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownDuration = countDownDuration.minus(oneSecondDuration);
            long minutes = countDownDuration.toMinutes();
            long seconds = countDownDuration.getSeconds() % 60;
            System.out.printf("Remaining Time: %02d:%02d%n", minutes, seconds);
        }
        System.out.println("Time's up!");

        // 17. Write a method to determine the next business day (skip weekends).
        System.out.println(nextBusinessDay(LocalDate.now()));
        System.out.println(nextBusinessDay(LocalDate.now().plusDays(3)));

        // 18. Create a function to generate time slots of 30 minutes between 9 AM and 5
        // PM.
        timeSlots(LocalTime.of(9, 0), LocalTime.of(17, 0));

        // 19. Convert a `LocalDateTime` from one timezone to another (e.g., UTC to  PST).
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime utcZonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));
        System.out.println("UTC: " + utcZonedDateTime);
        ZonedDateTime pstZonedDateTime = utcZonedDateTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println("PST: " + pstZonedDateTime);
        
        // 20. Schedule tasks for the next 5 Mondays at 9:00 AM in system timezone.
        LocalDate taskDate = LocalDate.now();
        taskDate = taskDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        LocalDateTime taskDateTime = LocalDateTime.of(taskDate, LocalTime.of(9, 0));
        ZonedDateTime taskZonedDateTime = ZonedDateTime.of(taskDateTime, ZoneId.systemDefault());
        for (int i = 0; i < 5; i++) {
            System.out.println(taskZonedDateTime);
            taskZonedDateTime = taskZonedDateTime.plusWeeks(1);
        }
    }

}
