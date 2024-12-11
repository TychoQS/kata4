package software.ulpgc.kata4.app.windows;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.kata4.architecture.model.Barchart;
import software.ulpgc.kata4.architecture.model.BarchartElement;

public class JFreeBarchartAdapter {
    public static JFreeChart adapt(Barchart barchart) {
        JFreeChart chart = ChartFactory.createBarChart(barchart.getTitle(), barchart.getxAxis(), barchart.getyAxis(), datasetOf(barchart));
        setUpChart(chart);
        return chart;
    }

    private static void setUpChart(JFreeChart chart) {
        chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_90);
    }

    private static CategoryDataset datasetOf(Barchart barchart) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (BarchartElement barchartElement : barchart) {
            dataset.addValue(barchartElement.getValue(), "", barchartElement.getField());
        }
        return dataset;
    }
}
