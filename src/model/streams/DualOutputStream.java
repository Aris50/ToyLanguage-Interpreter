package model.streams;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.OutputStream;
import java.io.PrintStream;

public class DualOutputStream extends OutputStream {
    private final PrintStream fileStream;
    private final ListView<String> outputListView;
    private final ListView<String> stackListView;
    private final ListView<String> heapListView;
    private final ListView<String> symTableListView;
    private final ListView<String> fileTableListView;
    private final ListView<String> prgStateIDListView;
    private final Label prgStateIDLabel;

    public DualOutputStream(PrintStream fileStream, ListView<String> outputListView, ListView<String> stackListView, ListView<String> heapListView, ListView<String> symTableListView, ListView<String> fileTableListView, ListView<String> prgStateIDListView, Label prgStateIDLabel) {
        this.fileStream = fileStream;
        this.outputListView = outputListView;
        this.stackListView = stackListView;
        this.heapListView = heapListView;
        this.symTableListView = symTableListView;
        this.fileTableListView = fileTableListView;
        this.prgStateIDListView = prgStateIDListView;
        this.prgStateIDLabel = prgStateIDLabel;
    }

    @Override
    public void write(int b) {
        fileStream.write(b);
        Platform.runLater(() -> outputListView.getItems().add(String.valueOf((char) b)));
    }

    @Override
    public void write(byte[] b, int off, int len) {
        stackListView.getItems().clear();
        symTableListView.getItems().clear();
        fileTableListView.getItems().clear();
        heapListView.getItems().clear();
        prgStateIDListView.getItems().clear();
        String text = new String(b, off, len);
        String filteredText = text.split("@")[0];
        fileStream.write(filteredText.getBytes(), 0, filteredText.length());
       // Platform.runLater(() -> outputListView.getItems().add(filteredText)); IF WE WANT THE WHOLE FILE CONTENTS
        String[] lines = text.split("@");
        String stackStr = lines[1];
        String symTableStr = lines[2];
        String fileTableStr = lines[3];
        String heapStr = lines[4];
        String prgStateIDStr = lines[5];
        String forTheLabel=lines[6];
        String outStr = lines[7];
        Platform.runLater(() -> stackListView.getItems().add(stackStr));
        Platform.runLater(() -> symTableListView.getItems().add(symTableStr));
        Platform.runLater(() -> fileTableListView.getItems().add(fileTableStr));
        Platform.runLater(() -> heapListView.getItems().add(heapStr));
        Platform.runLater(() -> prgStateIDListView.getItems().add(prgStateIDStr));
        Platform.runLater(() -> prgStateIDLabel.setText(forTheLabel));
        Platform.runLater(() -> outputListView.getItems().add(outStr));

    }


    @Override
    public void flush() {
        fileStream.flush();
    }

    @Override
    public void close() {
        fileStream.close();
    }
}
