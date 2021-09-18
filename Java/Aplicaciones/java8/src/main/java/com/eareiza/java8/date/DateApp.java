package com.eareiza.java8.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.DateFormatter;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class DateApp {
	
	//LocalDate -- LocalTime -- LocalDateTime
	public void verificar(int version) {
		if(version == 7) {
			Calendar fecha1 = Calendar.getInstance();
			Calendar fecha2 = Calendar.getInstance();
			fecha1.set(1982, 9, 14);
			System.out.println(fecha1.after(fecha2));
		}else if(version == 8) {
			//Trabajando con fechas
			System.out.println("Fechas:");
			LocalDate fecha1 = LocalDate.of(1982, 10, 14);
			LocalDate fecha2 = LocalDate.now();
			System.out.println(fecha1.isAfter(fecha2));
			System.out.println(fecha1.isBefore(fecha2));
			
			//Trabajando con tiempo
			System.out.println("Tiempo:");
			LocalTime tiempo1 = LocalTime.of(7, 50, 30);
			LocalTime tiempo2 = LocalTime.now();
			
			System.out.println(tiempo1.isAfter(tiempo2));
			System.out.println(tiempo1.isBefore(tiempo2));
			
			//Trabajando con fechas y hora
			System.out.println("Fecha y Hora:");
			LocalDateTime fecCom1 = LocalDateTime.of(1982, 10, 14, 23, 12, 35);
			LocalDateTime fecCom2 = LocalDateTime.of(1982, 10, 14, 23, 12, 15);
			System.out.println(fecCom1.isAfter(fecCom2));
			System.out.println(fecCom1.isBefore(fecCom2));
			
			//sumar o restar días y minutos
			System.out.println("sumar o restar días facilmente");
			LocalDateTime localDateTimePlus = fecCom1.plusDays(5);
	        System.out.println(localDateTimePlus); // 2017-08-25T08:30
	        LocalDateTime localDateTimeMinus = fecCom1.minusMinutes(10);
	        System.out.println(localDateTimeMinus); // 2017-08-25T08:20
			
			//sumar o restar días facilmente
			System.out.println("sumar o restar días facilmente");
			LocalDate datePlus = fecha1.plusDays(7);
	        System.out.println(datePlus.toString());  

	        LocalDate dateMinus = fecha1.minusDays(7);
	        System.out.println(dateMinus.toString()); // 2017-10-03
	        System.out.println();
		}
	}

	//Medir tiempo
	public void medirTiempo(int version) throws InterruptedException{
		if (version == 7) {
			System.out.println(System.currentTimeMillis());
			long inicio = System.currentTimeMillis();
			Thread.sleep(1000);
			long fin = System.currentTimeMillis();
			System.out.println(fin - inicio);
		}else if (version == 8) {
			Instant inicio = Instant.now();
			Thread.sleep(120000);
			Instant fin = Instant.now();
			System.out.println(Duration.between(inicio, fin).toMinutes());
		}
	}
	
	//Periodo entre fechas
	public void periodoFechas(int version) {
		if(version == 7) {
			Calendar nacimiento = Calendar.getInstance();
			Calendar actual = Calendar.getInstance();			
			nacimiento.set(1982, 9, 14);			
			int anios = 0;
			
			while(nacimiento.before(actual)) {
				nacimiento.add(Calendar.YEAR, 1);
				if(nacimiento.before(actual)) {
					anios++; 
				}
			}
			System.out.println(anios);
		}else if (version == 8) {
			LocalDate nacimiento = LocalDate.of(1982, 10, 14);
			LocalDate actual = LocalDate.now();
			
			Period periodo = Period.between(nacimiento, actual);
			System.out.println("Han transcurrido "+periodo.getYears()+" , "+periodo.getMonths()
			+" meses y "+periodo.getDays()+" dias desde mi nacimiento");
		}
	}

	
	//Periodo de tiempo, clase Duration	
	public void periodoTiempo() {
		System.out.println("Periodo de tiempo");
		LocalTime startLocalTime = LocalTime.of(8, 30);
        LocalTime endLocalTime = startLocalTime.plus(Duration.ofHours(3));  // 11:30

        long diffSeconds = Duration.between(startLocalTime, endLocalTime).getSeconds();
        System.out.println(diffSeconds); // 10800 seconds
	}
	
	//Convertir fechas
	public void convertir(int version) throws  java.text.ParseException{
		if (version == 7) {
			//convertir de cadena a fecha
			String fechaCad = "1982/10/14";
			DateFormat fmd =  new SimpleDateFormat("yyyy/MM/dd");
			Date fecha = fmd.parse(fechaCad);
			System.out.println(fecha);
			//convertir de fecha a cadena
			Date fechaActual = Calendar.getInstance().getTime();
			fmd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
			String fechaCadena = fmd.format(fechaActual);
			System.out.println(fechaCadena);
		}else if(version == 8) {
			//convertir de cadena a fecha
			String fechaCad = "1982/10/14";
			DateTimeFormatter fmd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate fechaNac = LocalDate.parse(fechaCad,fmd);
			System.out.println(fechaNac);
			System.out.println(fmd.format(fechaNac));
			fmd = DateTimeFormatter.ofPattern("yyyyMMdd");
			System.out.println(fmd.format(fechaNac));
		}
	}
	
	//ZonedDateTime -- ZoneId
	public void zonaHoraria() {
		System.out.println("Zona Horaria");
		//ZoneId.getAvailableZoneIds().stream().sorted((x,y)->y.compareTo(x)).forEach(System.out::println);
		ZoneId zona = ZoneId.of("America/Buenos_Aires");
		
		LocalDateTime localDateTimeOf = LocalDateTime.of(2017, Month.AUGUST, 20, 8, 30);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTimeOf, zona);
        System.out.println(zonedDateTime); // 2017-08-20T08:30-05:00[America/Buenos_Aires]
        ZonedDateTime tokyoDateTime = localDateTimeOf.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println(tokyoDateTime); // 2017-08-20T08:30+09:00[Asia/Tokyo]
	}
	
	public static void main(String[] args) {
		DateApp fechaApp = new DateApp();
		//fechaApp.verificar(8);
		//fechaApp.periodoFechas(8);
		//fechaApp.zonaHoraria();
		fechaApp.periodoTiempo();
		try {
			//fechaApp.medirTiempo(8);
			fechaApp.convertir(8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
