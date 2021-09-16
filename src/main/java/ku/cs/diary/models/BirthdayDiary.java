package ku.cs.diary.models;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class BirthdayDiary {
    private Map<String, LocalDate> birthdays;

    public BirthdayDiary() {
        birthdays = new TreeMap<>();
    }

    /**
     * เพิ่มข้อมูลวันเกิดของบุคคล
     * @param name ชื่อบุคคล
     * @param day วันเกิด (1 - 31)
     * @param month เดือนเกิด (1: มกราคม, 2: กุมภาพันธ์, ...)
     * @param year ปีค.ศ.ที่เกิด
     */
    public void addBirthday(String name, int day, int month, int year) {
        LocalDate birthday = LocalDate.of(year, month, day);
        birthdays.put(name, birthday);
    }

    /**
     * ต้องการวันเดือนปีเกิดของบุคคล
     * @param name ชื่อของบุคคล
     * @return
     */
    public LocalDate getBirthdayFor(String name) { return birthdays.get(name); }

    /**
     * ต้องการอายุของบุคคลนั้นในปี ค.ศ. ที่กำหนด
     * @param name ชื่อของบุคคล
     * @param year ปี ค.ศ.
     * @return อายุของบุคคล
     */
    public int getAgeInYear(String name, int year) {
        LocalDate birthday = getBirthdayFor(name);
        if (birthday == null){
            throw new RuntimeException("Birthday not found for this name " + name);
        }
        int yearOfDate = birthday.getYear();
        if (year - yearOfDate >= 0){
            return year - yearOfDate;
        }
        throw new RuntimeException(name + "was born after year " + year);
    }

    /**
     * ต้องการ Set ของชื่อบุคลล ที่ในปีที่กำหนด มีอายุตามที่กำหนด
     * @param age อายุของบุคคล
     * @param year ปี ค.ศ.
     * @return Set ของชื่อบุคคล
     */
    public Set<String> getFriendsOfAgeIn(int age, int year) {
        Set<String> people = new TreeSet<>();
        for (String name: birthdays.keySet()){
            if (this.getAgeInYear(name, year) == age){
                people.add(name);
            }
        }
        return people;
    }

    /**
     * ต้องการ Set ของชื่อบุคคลที่เกิดในเดือนที่กำหนด
     * @param month เดือน
     * @return Set ของชื่อบุคคล
     */
    public Set<String> getBirthdaysIn(Month month) {
        Set<String> people = new TreeSet<>();
        for (String name: birthdays.keySet()){
            if (this.getBirthdayFor(name).getMonth().equals(month)){
                people.add(name);
            }
        }
        return people;
    }

    /**
     * ต้องการ Set ของชื่อบุคคลที่เกิดในเดือนปัจจุบัน
     * @return Set ของชื่อบุคคล
     */
    public Set<String> getBirthdaysInCurrentMonth() {
        return this.getBirthdaysIn(LocalDate.now().getMonth());
    }

    /**
     * ต้องการผลรวมของอายุของทุกคนในปีนี้
     * @return ผลรวมของอายุ
     */
    public int getTotalAgeInYears() {
        int total = 0;
        for (String name: birthdays.keySet()){
            total += this.getAgeInYear(name, LocalDate.now().getYear());
        }
        return total;
    }

    @Override
    public String toString() {
        String result = "";
        for(String name: birthdays.keySet()){
            LocalDate date = this.getBirthdayFor(name);
            result += name + "," + date.getDayOfMonth() + "," +
                    date.getMonthValue() + "," + date.getYear() + "\n";
        }
        return result;
    }
}
