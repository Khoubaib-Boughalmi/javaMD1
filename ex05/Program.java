import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author kboughal
 */

public class Program {
  public static boolean validateName(String name) {
    if (name == null || name.length() > 10) return false;
    for (int i = 0; i < name.length(); ++i) {
      if (name.charAt(i) == ' ' || 
        !(name.charAt(i) >= 'A' &&  name.charAt(i) <= 'Z' ) &&
        !(name.charAt(i) >= 'a' &&  name.charAt(i) <= 'z' )
        ) return false;
    }
    return  true;
  }

  public static String[] createStudentNamesArray(String namesString, int studentCount) {

    String []studentsNames = new String[studentCount];

    int x = 0;
    int j = 0;
    String name = "";
    while (j < namesString.length()) {
      if (namesString.charAt(j) != ' ') name += namesString.charAt(j);
      else if (!name.equals("")){
        studentsNames[x] = name;
        x++;
        name = "";
      }
      j++;
    }
    if (!namesString.isEmpty()) studentsNames[x] = name;
    return studentsNames; 
  }
public static String[][] createStudentScheduleArray(String scheduleString, int classesCount) {
    String[][] studentsSchedule = new String[classesCount][2];
    int i = 0;
    int j = 0;
    while (i < scheduleString.length()) {
        String session = "";
        String day = "";
        while (i < scheduleString.length() && scheduleString.charAt(i) != ' ') session += scheduleString.charAt(i++);
        if (i < scheduleString.length() && scheduleString.charAt(i) == ' ') i++;
        while (i < scheduleString.length() && scheduleString.charAt(i) != '\n') day += scheduleString.charAt(i++);
        if (i < scheduleString.length() && scheduleString.charAt(i) == '\n') i++;
        studentsSchedule[j][0] = session;
        studentsSchedule[j][1] = day;
        j++;
    }
    
    // Improved sorting method
    Arrays.sort(studentsSchedule, new Comparator<String[]>() {
        @Override
        public int compare(String[] a, String[] b) {
            // First, compare by day
            int dayComparison = a[1].compareTo(b[1]);
            if (dayComparison != 0) {
                return dayComparison;
            }
            
            // If days are the same, compare by time
            return a[0].compareTo(b[0]);
        }
    });
    
    return studentsSchedule;
}


  public static String[][] parseAttendanceRecord(Scanner scanner) {
    String[][] attendanceRecord = new String[10][4];
    int attendanceCount = 0;
    
    while (scanner.hasNextLine()) {
        String attendanceLine = scanner.nextLine();
        if (attendanceLine.equals(".")) break;

        String studentName = "";
        String time = "";
        String date = "";
        String status = "";
        int state = 0;

        for (int i = 0; i < attendanceLine.length(); i++) {
            char c = attendanceLine.charAt(i);
            
            if (c == ' ') {
                state++;
                continue;
            }

            switch(state) {
                case 0 -> studentName += c;
                case 1 -> time += c;
                case 2 -> date += c;
                case 3 -> status += c;
            }
        }

        if (!studentName.isEmpty() && !time.isEmpty() && 
            !date.isEmpty() && !status.isEmpty()) {
            attendanceRecord[attendanceCount][0] = studentName;
            attendanceRecord[attendanceCount][1] = time;
            attendanceRecord[attendanceCount][2] = date;
            attendanceRecord[attendanceCount][3] = status;
            attendanceCount++;
        }

        if (attendanceCount == 10) break;
    }

    return attendanceRecord;
  }

  public static void displayScheduleAndAttendance(
    String[] studentsNames,
    String[][] studentsSchedule,
    String[][] attendanceRecord
) {
    String[] daysOfTheWeek = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};
    int daysNumber = 1;
    List<String[]> matchedStudentsSchedule = new ArrayList<>();

    System.out.printf("%11s", "");
    while (daysNumber < 31) {
        for (int j = 0; j < daysOfTheWeek.length; ++j) {
            for (int i = 0; i < studentsSchedule.length; ++i) {
                if (studentsSchedule[i][1].equals(daysOfTheWeek[j])) {
                    System.out.printf("%1s:00%3s%3d|", studentsSchedule[i][0], daysOfTheWeek[j], daysNumber);
                    matchedStudentsSchedule.add(new String[]{studentsSchedule[i][0], String.valueOf(daysNumber)});
                }
            }
            daysNumber++;
            if (daysNumber > 30) break;
        }
    }
    System.out.println();

    for (int i = 0; i < studentsNames.length; ++i) {
        System.out.printf("%-10s|", studentsNames[i]);
        
        for (String[] session : matchedStudentsSchedule) {
            boolean attended = false;
            for (String[] attendance : attendanceRecord) {
                if (studentsNames[i].equals(attendance[0]) && 
                    session[0].equals(attendance[1]) && 
                    session[1].equals(attendance[2])) {
                    System.out.printf("%10s|", attendance[3].equals("HERE")? "1" : "-1");
                    attended = true;
                    break;
                }
            }
            if (!attended) {
                System.out.printf("%10s|", "");
            }
        }
        System.out.println();
    }
}
  public static void main(String []args) {
    int i = 1;
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String namesString = "";
      int studentCount = 0;
      while (scanner.hasNext()) {
          String name = scanner.nextLine();
          if (name.equals(".")) break;
          if (!validateName(name)) {
            System.err.println("Invalid Input");
            System.exit(-1);
          }
          namesString += " " + name;
          studentCount += 1;
      }

      String[] studentsNames = createStudentNamesArray(namesString, studentCount);

      String scheduleString = "";
      int classesCount = 0;
      while (true) {
        if (scanner.hasNext()) {
           String classTime = scanner.nextLine();
          if (classTime.equals(".")) break;
          scheduleString += classTime + "\n";
          classesCount += 1;
          if (classesCount == 10) break;
        }
      }
      String[][] studentsSchedule = createStudentScheduleArray(scheduleString, classesCount);
      String[][] attendanceRecord = parseAttendanceRecord(scanner);

      displayScheduleAndAttendance(studentsNames, studentsSchedule, attendanceRecord);
      if (++i == 3) break;
    }
    scanner.close();
  }
}
