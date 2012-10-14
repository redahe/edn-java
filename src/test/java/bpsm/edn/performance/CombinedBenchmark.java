package bpsm.edn.performance;


public class CombinedBenchmark extends ABenchmark {

    public void time_combined(int reps) {
        parse(reps, "large-keyword-map.edn");
        parse(reps, "large-symbol-map.edn");
        parse(reps, "list-of-nil.edn");
        parse(reps, "map-of-maps.edn");
        parse(reps, "map-tree.edn");
        parse(reps, "mixed-vector.edn");
        parse(reps, "set-of-keywords.edn");
        parse(reps, "set-of-longs.edn");
        parse(reps, "set-of-symbols.edn");
        parse(reps, "vecor-of-maps.edn");
        parse(reps, "vector-of-bigdecs.edn");
        parse(reps, "vector-of-bigints.edn");
        parse(reps, "vector-of-booleans.edn");
        parse(reps, "vector-of-chars.edn");
        parse(reps, "vector-of-doubles.edn");
        parse(reps, "vector-of-instants.edn");
        parse(reps, "vector-of-ints.edn");
        parse(reps, "vector-of-keywords.edn");
        parse(reps, "vector-of-longs.edn");
        parse(reps, "vector-of-nil.edn");
        parse(reps, "vector-of-strings.edn");
        parse(reps, "vector-of-symbols.edn");
        parse(reps, "vector-of-uuid.edn");
        parse(reps, "vector-of-vectors.edn");
        parse(reps, "vector-tree.edn");
    }

    public static void main(String[] args) {
        new CombinedBenchmark().run();
    }

}