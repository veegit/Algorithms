package com.vee.algorithms.others;

import java.util.*;
import java.util.stream.Collectors;
/**
 * /*
 * Assign kids to teachers
 
 Inputs:
  - List of kids (Strings)
  - List of teachers (Strings)
  - Max class size (int)
  
 Output:
  - Assignments - Map<String, List<String>>
  - If there are kids without a spot - assign them to a teacher named "No Teacher"

 Requirements:
  - Every teacher has the same number of kids (as close as possible)
  - Kids get a spot on a first come first served basis
  
 Example:
  - 11 kids and 3 teachers - two teachers have 4 kids and one teacher has 3 kids
  - If max class size = 3, then kid 10 and kid 11 would be assigned to "No Teacher"
 */
class AssignmentKidsTeachers {
}

/*class
public static void main(String[] args) {
    List<String> kids = new ArrayList<String>();
    List<String> teachers = new ArrayList<String>();
    
    for(int i = 1; i <= 3; i++) {
       teachers.add("Teacher " + i);
    }
    
    for(int i = 1; i <= 20; i++) {
       kids.add("Kid " + i);
    }
    
    Map<String, List<String>> results1 = getSplits(kids, teachers);
    Map<String, List<String>> results = getSplits(kids, teachers, 3);
    
    System.out.println(Arrays.toString(results.entrySet().toArray()));
    
  }
  
  public static Map<String, List<String>> getSplits(List<String> kids, List<String> teachers) {
    Map<String, List<String>> map = new HashMap<>();
    int numTeachers = teachers.size();
    int numKids = kids.size();
    for (String t : teachers) {
      map.put(t, new ArrayList<>()); 
    }
    int count = numTeachers;
    Iterator<Map.Entry<String, List<String>>> it = map.entrySet().iterator();
    for (String kid: kids) {
      if (count == 0) {
        count = numTeachers;
        it = map.entrySet().iterator();
      }
      it.next().getValue().add(kid);
      count--;
    }
    return map;
  }
  
  public static Map<String, List<String>> getSplits(List<String> kids, List<String> teachers, int maxSize) {
    Map<String, List<String>> map = new HashMap<>();
    int numTeachers = teachers.size();
    int numKids = kids.size();
    for (String t : teachers) {
      map.put(t, new ArrayList<>()); 
    }
    int count = numTeachers;
    Iterator<Map.Entry<String, List<String>>> it = map.entrySet().iterator();
    List<String> noTeacherClass = new ArrayList<>();
    for (String kid: kids) {
      if (count == 0) {
        count = numTeachers;
        it = map.entrySet().iterator();
      }
      List<String> teacherClass = it.next().getValue();

      if (teacherClass.size() == maxSize) {
        noTeacherClass.add(kid);
      } else {
        teacherClass.add(kid);
      }
      count--;
    }
    map.put("No Teacher", noTeacherClass);
    return map;
  }



*/