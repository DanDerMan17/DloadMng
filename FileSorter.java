import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FileSorter {

    public static void main(String[] args) {
        String downloadFolder = "C:/Users/Startklar/Downloads";

        String pdfFolder = "C:/Users/Startklar/Downloads/Pdf";
        String applicationsFolder = "C:/Users/Startklar/Downloads/Apps";
        String excelFolder = "C:/Users/Startklar/Downloads/Excel";
        String wordFolder = "C:/Users/Startklar/Downloads/Word";
        String picFolder = "C:/Users/Startklar/Downloads/Pictures";
        String zipFolder = "C:/Users/Startklar/Downloads/ZipFiles";

        List<String> pdfFiles = new ArrayList<>();
        List<String> appFiles = new ArrayList<>();
        List<String> excelFiles = new ArrayList<>();
        List<String> wordFiles = new ArrayList<>();
        List<String> picFiles = new ArrayList<>();
        List<String> zipFiles = new ArrayList<>();

        try {
            Files.createDirectories(Path.of(pdfFolder));
            Files.createDirectories(Path.of(applicationsFolder));
            Files.createDirectories(Path.of(excelFolder));
            Files.createDirectories(Path.of(wordFolder));
            Files.createDirectories(Path.of(picFolder));
            Files.createDirectories(Path.of(zipFolder));

            Files.list(Path.of(downloadFolder)).forEach(file -> {
                String fileName = file.getFileName().toString();
                if (fileName.endsWith(".pdf")) {
                    pdfFiles.add(fileName);
                } else if (fileName.endsWith(".msi") || fileName.endsWith(".exe")) {
                    appFiles.add(fileName);
                } else if (fileName.endsWith(".xlsx")) {
                    excelFiles.add(fileName);
                } else if (fileName.endsWith(".docx") || fileName.endsWith(".odt") || fileName.endsWith(".doc")) {
                    wordFiles.add(fileName);
                } else if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg")) {
                    picFiles.add(fileName);
                } else if (fileName.endsWith(".zip")) {
                    zipFiles.add(fileName);
                }
            });

            List<String> totalFiles = new ArrayList<>();
            totalFiles.addAll(pdfFiles);
            totalFiles.addAll(appFiles);
            totalFiles.addAll(excelFiles);
            totalFiles.addAll(wordFiles);
            totalFiles.addAll(picFiles);
            totalFiles.addAll(zipFiles);

            int idx = 0;
            for (String file : totalFiles) {
                String srcPath = downloadFolder + File.separator + file;
                String destPath = "";

                if (file.endsWith(".pdf")) {
                    destPath = pdfFolder + File.separator + file;
                } else if (file.endsWith(".msi") || file.endsWith(".exe")) {
                    destPath = applicationsFolder + File.separator + file;
                } else if (file.endsWith(".xlsx")) {
                    destPath = excelFolder + File.separator + file;
                } else if (file.endsWith(".docx") || file.endsWith(".odt") || file.endsWith(".doc")) {
                    destPath = wordFolder + File.separator + file;
                } else if (file.endsWith(".jpg") || file.endsWith(".png") || file.endsWith(".jpeg")) {
                    destPath = picFolder + File.separator + file;
                } else if (file.endsWith(".zip")) {
                    destPath = zipFolder + File.separator + file;
                }

                try {
                    Files.move(Path.of(srcPath), Path.of(destPath), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                idx++;
                System.out.println("Sorting progress: " + idx + "/" + totalFiles.size());
            }

            System.out.println("Sorting was successful");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
