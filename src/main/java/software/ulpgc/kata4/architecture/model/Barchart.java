package software.ulpgc.kata4.architecture.model;

import java.util.Iterator;
import java.util.List;

public class Barchart implements Iterable<BarchartElement> {
    private final String title;
    private final String xAxis;
    private final String yAxis;
    private final List<BarchartElement> barchartElements;

    public Barchart(String title, String xAxis, String yAxis, List<BarchartElement> barchartElements) {
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.barchartElements = barchartElements;
    }

    public Iterator<BarchartElement> iterator() {
        return barchartElements.iterator();
    }

    public String getTitle() {
        return title;
    }

    public String getxAxis() {
        return xAxis;
    }

    public String getyAxis() {
        return yAxis;
    }
}
