package a02b.e1;

import java.util.*;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

    @Override
    public UniversityProgram flexible() {
        return new UniversityProgram() {

            private Map<String, Pair<Sector, Integer>> courses = new HashMap<>();

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                courses.put(name, new Pair<>(sector, credits));
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                int credits = 0;
                for (var el : courseNames) {
                    credits += courses.get(el).get2();
                    if (credits == 60) {
                        return true;
                    }
                }
                return false;

            }

        };

    }

    @Override
    public UniversityProgram scientific() {
        return new UniversityProgram() {

            private Map<String, Pair<Sector, Integer>> courses = new HashMap<>();

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                courses.put(name, new Pair<Sector, Integer>(sector, credits));
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                int credits = 0;
                int totCred = 0;
                int mathCred = 0;
                int physicsCred = 0;
                for (var courseName : courseNames) {
                    var course = courses.get(courseName);
                    credits = course.get2();
                    totCred += credits;
                    if (course.get1() == Sector.MATHEMATICS) {
                        mathCred += credits;
                    } else if (course.get1() == Sector.PHYSICS) {
                        physicsCred += credits;
                    }

                }
                if (totCred == 60 && mathCred >= 12 && physicsCred >= 12) {
                    return true;
                }
                return false;

            }

        };
    }

    @Override
    public UniversityProgram shortComputerScience() {
        return new UniversityProgram() {

            private Map<String, Pair<Sector, Integer>> courses = new HashMap<>();

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                courses.put(name, new Pair<UniversityProgram.Sector, Integer>(sector, credits));
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                int credits = 0;
                int pcCred = 0;
                int totCred = 0;
                for (var courseName : courseNames) {
                    var course = courses.get(courseName);
                    credits = course.get2();
                    totCred += credits;
                    if (course.get1() == Sector.COMPUTER_SCIENCE || course.get1() == Sector.COMPUTER_ENGINEERING) {
                        pcCred += credits;
                    }

                }
                if (totCred >= 48 && pcCred >= 30) {
                    return true;
                }
                return false;
            }

        };
    }

    @Override
    public UniversityProgram realistic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realistic'");
    }

}
