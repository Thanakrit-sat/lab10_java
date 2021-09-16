package ku.cs.diary.models;

import ku.cs.services.BirthDayDiaryFileDataSource;
import ku.cs.services.DataSource;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BirthdayDiaryTest {
    @Test
    void testAddBirthDay(){
        DataSource<BirthdayDiary> dataSource;
        dataSource = new BirthDayDiaryFileDataSource();
        BirthdayDiary diary = dataSource.readData();

        LocalDate birthday = diary.getBirthdayFor("Panipak Wongpattanakit");
        LocalDate expected = LocalDate.of(1997, 8, 8);

        assertEquals(expected.toString(), birthday.toString());
    }

    @Test
    void testGetAgeInYear(){
        DataSource<BirthdayDiary> dataSource;
        dataSource = new BirthDayDiaryFileDataSource();
        BirthdayDiary diary = dataSource.readData();

        assertEquals(35, diary.getAgeInYear("Robert Downey Jr", 2000));
    }

    @Test
    void testGetFriendOfAgeInYear(){
        DataSource<BirthdayDiary> dataSource;
        dataSource = new BirthDayDiaryFileDataSource();
        BirthdayDiary diary = dataSource.readData();

        Set<String> people = diary.getFriendsOfAgeIn(50,2021);
        // Mariah Carey
        // Lance Armstrong
        // Ricky Martin
        assertEquals("[Lance Armstrong, Mariah Carey, Ricky Martin]",people.toString());
    }

    @Test
    void testGetBirthdaysIn () {
        DataSource<BirthdayDiary> dataSource;
        dataSource = new BirthDayDiaryFileDataSource();
        BirthdayDiary diary = dataSource.readData();

        LocalDate date = LocalDate.of(2021,10,1);
        Set<String> people = diary.getBirthdaysIn(date.getMonth());
        // Diego Maradona
        // Naomi Osaka
        assertEquals("[Diego Maradona, Naomi Osaka]",people.toString());
    }

    @Test
    void testGetBirthDaysInCurrentMouth(){
        DataSource<BirthdayDiary> dataSource;
        dataSource = new BirthDayDiaryFileDataSource();
        BirthdayDiary diary = dataSource.readData();

        Set<String> people = diary.getBirthdaysInCurrentMonth();
        //Current Mouth = September has 8 people
        assertEquals(8, people.size());

    }

    @Test
    void testGetTotalAgeInYears(){
        DataSource<BirthdayDiary> dataSource;
        dataSource = new BirthDayDiaryFileDataSource();
        BirthdayDiary diary = dataSource.readData();
        // Current Year is 2021 (Total age = 997 years)
        assertEquals(997, diary.getTotalAgeInYears());

    }

    @Test
    void testWriteData(){
        DataSource<BirthdayDiary> dataSource;
        dataSource = new BirthDayDiaryFileDataSource();
        BirthdayDiary diary = dataSource.readData();

        dataSource = new BirthDayDiaryFileDataSource("csv-data", "birthday-diary1.csv");
        dataSource.writeData(diary);

        assertTrue(true);
    }
}