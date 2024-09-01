package a01b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimeSheetFactoryImpl implements TimeSheetFactory {

    @Override
    public TimeSheet flat(int numActivities, int numNames, int hours) {
        return new TimeSheet() {
            List<String> days = new ArrayList<>();
            List<String> activity = new ArrayList<>();
            List<List<Integer>> data = new ArrayList<>();

            @Override
            public List<String> activities() {

                for (int i = 0; i <= numActivities; i++) {
                    activity.add("act" + i);
                }
                /*
                 * 
                 * for (var el : list) {
                 * list.add(activity);
                 * }
                 */
                return List.copyOf(activity);
            }

            @Override
            public List<String> days() {
                for (int i = 0; i <= numActivities; i++) {
                    days.add("day" + i);
                }
                return List.copyOf(days);
            }

            @Override
            public int getSingleData(String activity, String day) {
                for(int i=0; i < numActivities; i++) {
                    List<Integer> row = new ArrayList<>();
                    for(int j=0; j < num) {

                    }
                }
            }

            @Override
            public Map<String, Integer> sumsPerActivity() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'sumsPerActivity'");
            }

            @Override
            public Map<String, Integer> sumsPerDay() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'sumsPerDay'");
            }

        };
    }

    @Override
    public TimeSheet ofListsOfLists(List<String> activities, List<String> days, List<List<Integer>> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ofListsOfLists'");
    }

    @Override
    public TimeSheet ofRawData(int numActivities, int numDays, List<Pair<Integer, Integer>> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ofRawData'");
    }

    @Override
    public TimeSheet ofPartialMap(List<String> activities, List<String> days, Map<Pair<String, String>, Integer> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ofPartialMap'");
    }

}
