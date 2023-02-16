package com.glymed.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

import io.qameta.allure.Allure;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Reporter {


    public boolean wordReportCreator(String scenarioName, String excelFilePath, String wordFilePath, String screenshotPath, int sheetNo, int columnNo) {
        String wordFileName = null;
//		wordFileName = findRowText(scenarioName, excelFilePath,sheetNo,columnNo);
        if (wordFileName == null) {
            wordFileName = scenarioName;
        }
        return embedScreenshotsIntoWord(wordFilePath, screenshotPath, wordFileName, scenarioName);
    }

    public boolean embedScreenshotsIntoWord(String wordFilePath, String imagePath, String wordFileName, String scenarioName) {

        boolean status = false;
        InputStream pic = null;
        File file = null;
        File imageFolder = null;
        FileOutputStream out = null;
        XWPFDocument docx = null;
        try {
            docx = new XWPFDocument();
            XWPFParagraph para = docx.createParagraph();
            XWPFRun run = para.createRun();
            run.setText(scenarioName);
            para.setBorderLeft(Borders.BASIC_THIN_LINES);
            para.setBorderRight(Borders.BASIC_THIN_LINES);
            run.setBold(true);
            run.setFontSize(15);
            run.setUnderline(UnderlinePatterns.SINGLE);
            run.addBreak();
            para.setAlignment(ParagraphAlignment.CENTER);
            out = new FileOutputStream(wordFilePath + "/" + wordFileName + ".docx");
            imageFolder = new File(imagePath);
            System.out.println("Last Modified Time :");
            File[] filArr = imageFolder.listFiles();
            filArr = Arrays.stream(filArr).sorted(Comparator.comparingLong(f -> f.lastModified())).toArray(File[]::new);
            for (int i = 0; i < filArr.length; i++) {
                String screenshot_name = System.currentTimeMillis() + ".png";
                if (filArr[i].getName().toString().endsWith(".png")) {
                    Image image = ImageIO.read(filArr[i]);
                    BufferedImage buffered = (BufferedImage) image;
                    file = new File(wordFilePath + "/" + screenshot_name);
                    ImageIO.write(buffered, "png", file);
                    pic = new FileInputStream(file);
                    run.addBreak();
                    run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, screenshot_name, Units.toEMU(350), Units.toEMU(500));
                    run.addBreak();
                    run.addBreak();
                    run.setText(wordFileName + " - " + i + ".png");
                    TimeUnit.SECONDS.sleep(1);
                }
            }
            docx.write(out);
            for (File delFile : new File(imagePath).listFiles()) {
                if (delFile.getName().endsWith(".png"))
                    delFile.delete();
            }
            status = true;
        } catch (Exception e) {
            Log.info(e.getMessage());
        } finally {
            try {
                pic.close();
                out.flush();
                out.close();
                docx.close();
            } catch (IOException e) {
                Log.info(e.getMessage());
            }
        }
        return status;
    }

    public void addAllureAttachment(String name, Object file) throws IOException {
        if (file instanceof File[]) {
            for (File fi : (File[]) file) {
                switch (FilenameUtils.getExtension(fi.getName())) {
                    case "json":
                        Allure.getLifecycle().addAttachment(name, "application/json", "json",
                                FileUtils.convertFileToByteArray(fi));
                        break;
                    case "jpeg":
                        Allure.getLifecycle().addAttachment(name, "application/jpeg", "jpeg",
                                FileUtils.convertFileToByteArray(fi));
                        break;
                    case "png":
                        Allure.getLifecycle().addAttachment(name, "application/png", "png",
                                FileUtils.convertFileToByteArray(fi));
                        break;
                    case "html":
                        Allure.getLifecycle().addAttachment(name, "application/html", "html",
                                FileUtils.convertFileToByteArray(fi));
                        break;
                    default:
                }
            }
        } else if (file instanceof String) {
            Allure.getLifecycle().addAttachment(name, "application/json", "json", ((String) file).getBytes("UTF-8"));

        }
    }

}
