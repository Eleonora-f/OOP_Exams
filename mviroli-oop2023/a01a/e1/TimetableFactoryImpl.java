package a01a.e1;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimetableFactoryImpl implements TimetableFactory {

    @Override
    public Timetable empty() {
        return new Timetable() {
            Map<Pair<String, String>, Integer> timetable = new HashMap<>();

            @Override
            public Timetable addHour(String activity, String day) {
                var activiDay = new Pair<String, String>(activity, day);
                if (!timetable.containsKey(activiDay)) {
                    timetable.put(activiDay, 1);
                } else {
                    var hour = timetable.get(activiDay);
                    timetable.put(activiDay, hour + 1);
                }
                return this;

            }

            @Override
            public Set<String> activities() {
                return timetable.keySet().stream().map(t -> t.get1()).collect(Collectors.toSet());
            }

            @Override
            public Set<String> days() {
                return timetable.keySet().stream().map(t -> t.get2()).collect(Collectors.toSet());
            }

            @Override
            public int getSingleData(String activity, String day) {
                if (timetable.containsKey(new Pair<>(activity, day))) {
                    return timetable.get(new Pair<>(activity, day));
                }
                return 0;
            }

            @Override
            public int sums(Set<String> activities, Set<String> days) {
                int sum = 0;
                for (var act : activities) {
                    for (var day : days) {
                        if (timetable.containsKey(new Pair<>(act, day))) {
                            sum += timetable.get(new Pair<>(act, day));
                        }
                    }
                }
                return sum;
            }

        };
    }

    @Override
    public Timetable single(String activity, String day) {
        return new Timetable() {
            Map<Pair<String, String>, Integer> timetable = new HashMap<>();

            {
                // Blocco di inizializzazione per aggiungere l'elemento alla mappa
                timetable.put(new Pair<>(activity, day), 1);
            }

            @Override
            public Timetable addHour(String activity, String day) {
                var activiDay = new Pair<String, String>(activity, day);
                if (!timetable.containsKey(activiDay)) {
                    timetable.put(activiDay, 1);
                } else {
                    var hour = timetable.get(activiDay);
                    timetable.put(activiDay, hour + 1);
                }
                return this;

            }

            @Override
            public Set<String> activities() {
                return timetable.keySet().stream().map(t -> t.get1()).collect(Collectors.toSet());
            }

            @Override
            public Set<String> days() {
                return timetable.keySet().stream().map(t -> t.get2()).collect(Collectors.toSet());
            }

            @Override
            public int getSingleData(String activity, String day) {
                if (timetable.containsKey(new Pair<>(activity, day))) {
                    return timetable.get(new Pair<>(activity, day));
                }
                return 0;
            }

            @Override
            public int sums(Set<String> activities, Set<String> days) {
                int sum = 0;
                for (var act : activities) {
                    for (var day : days) {
                        if (timetable.containsKey(new Pair<>(act, day))) {
                            sum += timetable.get(new Pair<>(act, day));
                        }
                    }
                }
                return sum;
            }

        };
    }

    @Override
    public Timetable join(Timetable table1, Timetable table2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'join'");
    }

    @Override
    public Timetable cut(Timetable table, BiFunction<String, String, Integer> bounds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cut'");
    }

}
