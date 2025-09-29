package datetime;

import util.PrinterUtil;

import java.time.*;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

/**
 * Created by WillianF.Bueno on 21/09/2025
 * Exemplos didáticos da API de Data/Hora do Java 8 (java.time).
 */
public class DateTimeExamples {

    private static final String TITULO = "Java 8 Date/Time - Demonstrações";
    private static final ZoneId ZONE_SP = ZoneId.of("America/Sao_Paulo");
    private static final DateTimeFormatter FMT_DMY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FMT_DMY_HM = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final Locale PT_BR = new Locale("pt", "BR");

    static void main(String[] args) {
        PrinterUtil.run(TITULO, PrinterUtil.items(
                // LocalDate / LocalTime / LocalDateTime
                PrinterUtil.entry("LocalDate.now()", localDateNow()),
                PrinterUtil.entry("Criar LocalDate específico", localDateOf()),
                PrinterUtil.entry("LocalTime.now()", localTimeNow()),
                PrinterUtil.entry("LocalDateTime.now()", localDateTimeNow()),
                PrinterUtil.entry("Acesso a campos (ano/mês/dia)", accessFields()),

                // Parsing & Formatting
                PrinterUtil.entry("Parse ISO (yyyy-MM-dd)", parseIsoDate()),
                PrinterUtil.entry("Parse custom (dd/MM/yyyy)", parseCustomPtBr()),
                PrinterUtil.entry("Formatar custom (pt-BR)", formatCustomPtBr()),
                PrinterUtil.entry("Formatar localized (SHORT)", formatLocalizedShort()),

                // Aritmética
                PrinterUtil.entry("plusDays / minusWeeks / with", arithmeticDate()),
                PrinterUtil.entry("Truncar/alterar hora", arithmeticTime()),
                PrinterUtil.entry("Inicio e fim do mês (adjusters)", startEndOfMonth()),

                // Period / Duration
                PrinterUtil.entry("Period entre datas (anos/meses/dias)", periodBetweenDates()),
                PrinterUtil.entry("Duration entre horários (h/min/s)", durationBetweenTimes()),

                // TemporalAdjusters e ajustador custom
                PrinterUtil.entry("Próxima sexta-feira", nextFriday()),
                PrinterUtil.entry("Próximo dia útil (ajustador custom)", nextBusinessDay()),

                // ZoneId / ZonedDateTime / OffsetDateTime
                PrinterUtil.entry("Agora em São Paulo e UTC", nowInZones()),
                PrinterUtil.entry("Converter LocalDateTime -> ZonedDateTime", ldtToZoned()),
                PrinterUtil.entry("OffsetDateTime (UTC-03:00)", offsetDateTimeExample()),

                // Instant / epoch
                PrinterUtil.entry("Instant agora (epoch millis)", instantNowEpoch()),
                PrinterUtil.entry("Converter Instant <-> ZonedDateTime", instantZonedRoundTrip()),

                // Interop com java.util.Date
                PrinterUtil.entry("Converter Date -> Instant/LocalDateTime", legacyDateToJavaTime()),
                PrinterUtil.entry("Converter LocalDateTime -> Date", javaTimeToLegacyDate()),

                // Tipos utilitários
                PrinterUtil.entry("YearMonth (dias no mês)", yearMonthDays()),
                PrinterUtil.entry("MonthDay (aniversário)", monthDayBirthday()),
                PrinterUtil.entry("DayOfWeek (nome/l10n)", dayOfWeekNames())
        ));
    }

    /** LocalDate de hoje (sem hora). */
    private static String localDateNow() {
        LocalDate hoje = LocalDate.now();
        return "Hoje: " + hoje + " (ISO) | " + hoje.format(FMT_DMY);
    }

    /** Criar uma data específica. */
    private static String localDateOf() {
        LocalDate data = LocalDate.of(2025, Month.SEPTEMBER, 21);
        return "LocalDate.of(2025, SEPTEMBER, 21) -> " + data.format(FMT_DMY);
    }

    /** LocalTime de agora (somente hora). */
    private static String localTimeNow() {
        LocalTime agora = LocalTime.now();
        return "Hora atual: " + agora.withNano(0); // remove nanos pra ficar limpo
    }

    /** LocalDateTime de agora (data + hora). */
    private static String localDateTimeNow() {
        LocalDateTime agora = LocalDateTime.now();
        return "Agora: " + agora.withNano(0);
    }

    /** Acesso a campos individuais. */
    private static String accessFields() {
        LocalDate d = LocalDate.of(2025, 9, 21);
        int ano = d.getYear();
        Month mes = d.getMonth();
        int dia = d.getDayOfMonth();
        DayOfWeek dow = d.getDayOfWeek();
        return String.format("Data: %s | ano=%d, mes=%s(%d), dia=%d, diaSem=%s(%d)",
                d, ano, mes, mes.getValue(), dia, dow, dow.getValue());
    }

    /** Parse ISO padrão (yyyy-MM-dd). */
    private static String parseIsoDate() {
        LocalDate d = LocalDate.parse("2025-09-21");
        return "Parse ISO '2025-09-21' -> " + d.format(FMT_DMY);
    }

    /** Parse custom (dd/MM/yyyy). */
    private static String parseCustomPtBr() {
        LocalDate d = LocalDate.parse("05/01/2024", FMT_DMY);
        return "Parse pt-BR '05/01/2024' -> ISO: " + d;
    }

    /** Format custom em pt-BR. */
    private static String formatCustomPtBr() {
        LocalDateTime ldt = LocalDateTime.of(2024, 1, 5, 14, 30);
        return "Format custom: " + ldt.format(FMT_DMY_HM);
    }

    /** Format localized usando Locale pt-BR e estilo SHORT. */
    private static String formatLocalizedShort() {
        LocalDateTime ldt = LocalDateTime.of(2024, 7, 12, 9, 5);
        DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(PT_BR);
        return "Localized SHORT pt-BR: " + ldt.format(fmt);
    }

    /** Aritmética de datas: plus/minus/with. */
    private static String arithmeticDate() {
        LocalDate base = LocalDate.of(2025, 9, 21);
        LocalDate d1 = base.plusDays(10);
        LocalDate d2 = base.minusWeeks(2);
        LocalDate d3 = base.withMonth(12).withDayOfMonth(31);
        return String.format("Base=%s | +10d=%s | -2w=%s | with(12/31)=%s",
                base, d1, d2, d3);
    }

    /** Aritmética/ajustes em tempo. */
    private static String arithmeticTime() {
        LocalTime t = LocalTime.of(8, 15, 45);
        LocalTime t1 = t.plusHours(2).minusMinutes(10);
        LocalTime trunc = t.withSecond(0).withNano(0);
        return String.format("t=%s | +2h-10m=%s | trunc=%s", t, t1, trunc);
    }

    /** Início e fim do mês com TemporalAdjusters. */
    private static String startEndOfMonth() {
        LocalDate hoje = LocalDate.now();
        LocalDate ini = hoje.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate fim = hoje.with(TemporalAdjusters.lastDayOfMonth());
        return String.format("Hoje=%s | início=%s | fim=%s", hoje, ini, fim);
    }

    /** Period: diferença em anos/meses/dias (datas). */
    private static String periodBetweenDates() {
        LocalDate d1 = LocalDate.of(2020, 5, 10);
        LocalDate d2 = LocalDate.of(2025, 9, 21);
        Period p = Period.between(d1, d2);
        return String.format("Entre %s e %s -> %d anos, %d meses, %d dias",
                d1, d2, p.getYears(), p.getMonths(), p.getDays());
    }

    /** Duration: diferença em horas/min/seg (tempos/instantes). */
    private static String durationBetweenTimes() {
        LocalTime a = LocalTime.of(9, 0);
        LocalTime b = LocalTime.of(14, 45, 30);
        Duration dur = Duration.between(a, b);
        long h = dur.toHours();
        long m = dur.minusHours(h).toMinutes();
        long s = dur.minusHours(h).minusMinutes(m).getSeconds();
        return String.format("Entre %s e %s -> %dh %dm %ds", a, b, h, m, s);
    }

    /** Próxima sexta-feira a partir de hoje. */
    private static String nextFriday() {
        LocalDate hoje = LocalDate.now();
        LocalDate proxSex = hoje.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        return String.format("Hoje=%s | Próxima sexta=%s", hoje, proxSex);
    }

    /** Ajustador custom de próximo dia útil (seg-sex). */
    private static String nextBusinessDay() {
        TemporalAdjuster proximoUtil = temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int add = (dow == DayOfWeek.FRIDAY) ? 3
                    : (dow == DayOfWeek.SATURDAY) ? 2 : 1;
            return LocalDate.from(temporal).plusDays(add);
        };
        LocalDate hoje = LocalDate.now();
        LocalDate prox = hoje.with(proximoUtil);
        return String.format("Hoje=%s | Próximo útil=%s", hoje, prox);
    }

    /** Mostrar agora em SP e UTC. */
    private static String nowInZones() {
        ZonedDateTime sp = ZonedDateTime.now(ZONE_SP);
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        return "SP: " + sp.withNano(0) + " | UTC: " + utc.withNano(0);
    }

    /** Converter LocalDateTime para ZonedDateTime. */
    private static String ldtToZoned() {
        LocalDateTime ldt = LocalDateTime.of(2025, 9, 21, 10, 0);
        ZonedDateTime zdt = ldt.atZone(ZONE_SP);
        return ldt + " @ " + ZONE_SP + " -> " + zdt;
    }

    /** Exemplo de OffsetDateTime para -03:00. */
    private static String offsetDateTimeExample() {
        OffsetDateTime odt = OffsetDateTime.of(2025, 9, 21, 10, 0, 0, 0, ZoneOffset.ofHours(-3));
        return "OffsetDateTime(-03:00): " + odt;
    }

    /** Instant agora e epoch millis. */
    private static String instantNowEpoch() {
        Instant now = Instant.now();
        long epoch = now.toEpochMilli();
        return "Instant: " + now + " | epochMillis=" + epoch;
    }

    /** Converter Instant -> ZonedDateTime e voltar. */
    private static String instantZonedRoundTrip() {
        Instant i = Instant.now();
        ZonedDateTime sp = i.atZone(ZONE_SP);
        Instant back = sp.toInstant();
        return "Instant->SP->Instant: " + i + " -> " + sp.withNano(0) + " -> " + back;
    }

    /** Interop: java.util.Date -> Instant/LocalDateTime. */
    private static String legacyDateToJavaTime() {
        Date legado = new Date(1_700_000_000_000L); // exemplo fixo
        Instant i = legado.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(i, ZONE_SP);
        return "Date=" + legado + " -> Instant=" + i + " -> LDT@SP=" + ldt.withNano(0);
    }

    /** Interop: LocalDateTime -> java.util.Date. */
    private static String javaTimeToLegacyDate() {
        LocalDateTime ldt = LocalDateTime.of(2025, 9, 21, 13, 20);
        Instant i = ldt.atZone(ZONE_SP).toInstant();
        Date legado = Date.from(i);
        return "LDT=" + ldt + " @ SP -> Instant=" + i + " -> Date=" + legado;
    }

    /** YearMonth: descobrir dias no mês. */
    private static String yearMonthDays() {
        YearMonth ym = YearMonth.of(2024, Month.FEBRUARY);
        int len = ym.lengthOfMonth(); // 29 em 2024 (bissexto)
        YearMonth atual = YearMonth.now();
        return String.format("YearMonth=%s | dias=%d | atual=%s (dias=%d)",
                ym, len, atual, atual.lengthOfMonth());
    }

    /** MonthDay: checar aniversário independentemente do ano. */
    private static String monthDayBirthday() {
        MonthDay aniversario = MonthDay.of(Month.JANUARY, 5);
        MonthDay hoje = MonthDay.from(LocalDate.now());
        boolean ehHoje = hoje.equals(aniversario);
        return String.format("Aniversário=%s | Hoje=%s | é hoje? %s",
                aniversario, hoje, ehHoje ? "SIM" : "não");
    }

    /** DayOfWeek com nome localizado. */
    private static String dayOfWeekNames() {
        LocalDate d = LocalDate.of(2025, 9, 21);
        DayOfWeek dow = d.getDayOfWeek();
        // Nome curto/localizado
        String nomePt = dow.getDisplayName(java.time.format.TextStyle.FULL, PT_BR);
        String nomeEn = dow.getDisplayName(java.time.format.TextStyle.FULL, Locale.US);
        return String.format("%s é %s (pt-BR) / %s (en-US) | cronologia=%s",
                d, nomePt, nomeEn, IsoChronology.INSTANCE.getId());
    }
}
