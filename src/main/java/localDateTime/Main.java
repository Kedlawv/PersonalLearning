package localDateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.*;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        // Day of week and Month Enums

        DayOfWeek dow = DayOfWeek.MONDAY;
        Month m = Month.MAY;
        Locale locale = new Locale("PL");

        System.out.println(dow.getDisplayName(TextStyle.NARROW, locale));
        System.out.println(dow.getDisplayName(TextStyle.SHORT, locale));
        System.out.println(dow.getDisplayName(TextStyle.FULL, locale));

        System.out.println(DayOfWeek.FRIDAY.plus(2).getDisplayName(TextStyle.FULL, locale));

        System.out.println(m.getDisplayName(TextStyle.NARROW, locale));
        System.out.println(m.getDisplayName(TextStyle.SHORT, locale));
        System.out.println(m.getDisplayName(TextStyle.FULL, locale)); // Dlaczego to jest z malej ?
        System.out.println(m.getDisplayName(TextStyle.FULL, new Locale("en"))); // a to z duzej ?


        System.out.println(m.plus(2).getDisplayName(TextStyle.FULL, locale));
        System.out.println(m.maxLength());

        //LocalDate Class

        LocalDate date = LocalDate.of(2015, Month.AUGUST, 21);
        TemporalAdjuster tempAdj = TemporalAdjusters.next(DayOfWeek.MONDAY);
        LocalDate nextMon = date.with(tempAdj);

        System.out.printf("For the date %s next Monday is %s.%n", date, nextMon);

        YearMonth ym1 = YearMonth.now();
        YearMonth ym2 = YearMonth.of(2012, 6);
        YearMonth ym3 = YearMonth.of(2012, Month.JUNE);

        System.out.printf("%s: %d.%n", ym2, ym2.lengthOfMonth());

        MonthDay md1 = MonthDay.of(2, 25);
        System.out.println(md1);

        Year y = Year.of(2045);
        System.out.println(y);

        // Date and Time Classes

        LocalTime ltNow = LocalTime.now();
        System.out.println(ltNow);

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        LocalDateTime ldt2 = LocalDateTime.of(2015, 11, 23, 22, 5);
        System.out.println(ldt2);
        System.out.println(ldt2.plusDays(20));

        // https://docs.oracle.com/javase/tutorial/datetime/iso/datetime.html

        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMM yyyy -- HH:mm z");
        LocalDateTime ldt3 = LocalDateTime.of(2019, 5, 12, 19, 30);
        ZoneId zid = ZoneId.of("Poland");
        ZonedDateTime zdt = ZonedDateTime.of(ldt3, zid);
        String out1 = zdt.format(format);

        System.out.println("Not formatted: " + zdt);
        System.out.println("Formatted : " + out1 + " " + zid);
        System.out.println("\n\n\n_________________Instant Class_____________________");

        Instant timeStamp = Instant.now();
        System.out.println(timeStamp);
        Instant timeStamp2 = Instant.now().plusSeconds(60 * 60 + 15);
        System.out.println(timeStamp2);
        long secondsFromEpoch = Instant.ofEpochSecond(0l).until(Instant.now(), ChronoUnit.SECONDS);
        System.out.println(secondsFromEpoch);

        timeStamp2 = timeStamp2.plus(2, ChronoUnit.HOURS);
        System.out.println(timeStamp2);

        LocalDateTime ldtInst = LocalDateTime.ofInstant(timeStamp2, ZoneId.systemDefault());
        System.out.println(ldt);

        System.out.println("\n\n\n\n______________Parse and Format____________");

        String inputString = new String("24.06.1985");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String inputStringFromDate = LocalDate.now().toString();


        try {
            LocalDate pfDate = LocalDate.parse(inputString, formatter);
            System.out.println(pfDate);
        } catch (DateTimeParseException e) {
            System.out.printf("%s in not parsable%n.", inputString);
            e.printStackTrace();
        } catch (DateTimeException e) {
            System.out.printf("%s is not a valid pattern?");
        }

        System.out.println("\n-------------Temporal Adjusters ----------------------");

        LocalDate dateTA = LocalDate.of(2000, Month.OCTOBER, 15);
        DayOfWeek dowTA = date.getDayOfWeek();

        System.out.printf("%s is on %s%n", dateTA, dowTA);
        System.out.printf("First day of month is %s%n", dateTA.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.printf("First Monday of month is %s%n",
                dateTA.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        System.out.printf("Next Tuesday is %s%n",
                dateTA.with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));

        System.out.println("\n_______________Temporal Query______________");
        TemporalQuery<TemporalUnit> query = TemporalQueries.precision();
        System.out.printf("\nPrecision of LocalDate is %s%n", dateTA.query(query));

        System.out.println("\n___________________Duration and Period____________________");

        LocalDate dateNowDP = LocalDate.now();
        LocalDate birthday = LocalDate.of(1979, 12, 31);

        Period p = Period.between(birthday, dateNowDP);
        long p2 = ChronoUnit.DAYS.between(birthday, dateNowDP);

        System.out.printf("I am %s years old %s months and %s days, that is %s days total%n",
                p.getYears(), p.getMonths(), p.getDays(), p2);

        System.out.println("\n_____________Exercise 1______________");

        reportLengthOfMonthsInGivenYear(2000);
        reportLengthOfMonthsInGivenYearJava(2000);

        System.out.println("\n________________Exercise 2 ______________");
        getMondaysOfMonth(Month.JUNE);
        System.out.println("_____________While loop imp_______________");
        getMondaysOfMonthWhileLoop(Month.JUNE);
        System.out.println("\n_____________Java tut imp_________________");
        getMondaysOfMonthJava(Month.JUNE);

        System.out.println("_____________Exercise 3 ___________________");
        isFriday13th(LocalDate.of(2019,Month.SEPTEMBER,13));
        System.out.println(queryFrom(LocalDate.of(2019,Month.SEPTEMBER,13)));
        isFriday13th(LocalDate.now());
        System.out.println(queryFrom(LocalDate.now()));

    }

    //1. Write an example that, for a given year, reports the length of each month within that year.

    // My implementation
    public static void reportLengthOfMonthsInGivenYear(int year) {
        System.out.println("\n||||||||| My imp |||||||||||");
        LocalDate date = LocalDate.of(year, 1, 1);
        for (Month month : Month.values()) {
            System.out.println(month + " is " + date.lengthOfMonth() + " days long.");
            date = date.plusMonths(1);
        }
    }

    // Java Tutorial implementation

    public static void reportLengthOfMonthsInGivenYearJava(int year) {
        System.out.println("\n||||||||||| Java Imp ||||||||||||");
        for (Month month : Month.values()) {
            YearMonth ym = YearMonth.of(year, month);
            System.out.println(month + " is " + ym.lengthOfMonth() + " days long. ");
        }
    }

    // 2. Write an example that, for a given month of the current year, lists all of the Mondays in that month.

    public static void getMondaysOfMonth(Month month) {
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), month, 1);
        for (int i = 1; i <= date.getMonth().maxLength(); i++) {
            date = LocalDate.of(LocalDate.now().getYear(), month, i);
            if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                System.out.printf("%s is a %s%n", date, date.getDayOfWeek());
            }
        }

    }

    public static void getMondaysOfMonthWhileLoop(Month month) {
        int year = LocalDate.now().getYear();
        int day = 1;
        LocalDate date = LocalDate.of(year, month, 1);
        System.out.println(date);
        LocalDate dateEnd = LocalDate.of(year,month,date.lengthOfMonth());
        System.out.println(dateEnd);
        while (ChronoUnit.DAYS.between(date,dateEnd) > -1) {
            if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                System.out.printf("%s is a %s%n", date, date.getDayOfWeek());
            }
            date = date.plusDays(1);
        }

    }
    // Java tutorial implementation
    public static void getMondaysOfMonthJava(Month month){
        LocalDate date = Year.now().atMonth(month).atDay(1).
                with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        Month mth = date.getMonth();
        while(mth == month){
            System.out.printf("%s is a Monday%n",date);
            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            mth = date.getMonth();

        }

    }

    // 3. Write an example that tests whether a given date occurs on Friday the 13th.

    public static boolean isFriday13th(LocalDate date){
        DayOfWeek dof = date.getDayOfWeek();
        if(date.getDayOfMonth() == 13 && dof == DayOfWeek.FRIDAY){
            System.out.printf("%s is Friday 13th%n",date);
            return true;
        }else{
            System.out.printf("%s is not Friday 13th%n", date);
            return false;
        }
    }

    // Java tut implementation

    public static boolean queryFrom(TemporalAccessor date) {

        return ((date.get(ChronoField.DAY_OF_MONTH) == 13) &&
                (date.get(ChronoField.DAY_OF_WEEK) == 5));
    }


}
