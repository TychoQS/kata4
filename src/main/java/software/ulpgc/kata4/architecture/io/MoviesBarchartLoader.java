package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Barchart;
import software.ulpgc.kata4.architecture.model.BarchartElement;

import java.util.List;

public class MoviesBarchartLoader implements BarchartLoader{
    private final List<BarchartElement> titlesPerYearBarchartElements;
    private final List<BarchartElement> titlesTypeCountBarchartElements;

    public MoviesBarchartLoader(List<BarchartElement> titlesPerYearBarchartElements, List<BarchartElement> titlesTypeCountBarchartElements) {
        this.titlesPerYearBarchartElements = titlesPerYearBarchartElements;
        this.titlesTypeCountBarchartElements = titlesTypeCountBarchartElements;
    }

    @Override
    public Barchart load(int id) {
        return switch (id) {
            case 0 -> loadBarchart("Titles per year", "Year", "Nº of titles", titlesPerYearBarchartElements);
            case 1 -> loadBarchart("Titles type count", "Titles type", "Count", titlesTypeCountBarchartElements);
            default -> null;
        };
    }

    private Barchart loadBarchart(String titles, String xAxis, String yAxis, List<BarchartElement> barchartElements) {
        return new Barchart(titles, xAxis, yAxis, barchartElements);
    }
}
