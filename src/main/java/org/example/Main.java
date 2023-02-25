package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;


public class Main {
    
    static String url_example = "https://eu.levenhuk.com/catalogue/telescopes/?PAGEN_1=";
    static String url_testing = "https://cz.levenhuk.com/catalogue/hvezdarske-dalekohledy/?PAGEN_1=";
    
    
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get(url_example +"1");

        String countOfElString = driver.findElement(By.xpath("/html/body/main/div[2]/div/div/div[2]/div[4]/div[4]/div[1]/div/div/div")).getText();
        String substring = countOfElString.substring(10);

        String intString = substring.replaceAll("[^0-9]+", " ");


        int countOfElements = Integer.parseInt(intString.trim());
        int countOfPages = countOfElements / 21 + 1;

        List<String> articules_example = new ArrayList<>(); //RESULTS FOR https://eu.levenhuk.com/catalogue/telescopes/


        for (int i = 0; i < countOfPages; i++) {

            driver.navigate().to(url_example + i);

            WebElement container = driver.findElement(By.className("catalog-grid__row"));
            List<WebElement> elements = container.findElements(By.className("product-card__id"));
            for (WebElement we : elements) {
                String art = we.getText().replaceAll("[^0-9]+", " ");
                articules_example.add(art);
            }
            System.out.println("Parsed page " + (i+1));


        }

        System.out.println(articules_example.size());

        for (String str: articules_example){
            System.out.println(str);
        }


        driver.navigate().to(url_testing + "1");

        String countOfElString_testing = driver.findElement(By.xpath("/html/body/main/div[2]/div/div/div[2]/div[4]/div[4]/div[1]/div/div/div")).getText();
        String substring_testing = countOfElString_testing.substring(10);

        String intString_testing = substring_testing.replaceAll("[^0-9]+", " ");


        int countOfElements_testing = Integer.parseInt(intString_testing.trim());
        int countOfPages_testing = countOfElements_testing / 21 + 1;

        List<String> articules_testing = new ArrayList<>(); //RESULTS FOR https://eu.levenhuk.com/catalogue/telescopes/


        for (int i = 0; i < countOfPages_testing; i++) {

            driver.navigate().to(url_testing + i);

            WebElement container = driver.findElement(By.className("catalog-grid__row"));
            List<WebElement> elements = container.findElements(By.className("product-card__id"));
            for (WebElement we : elements) {
                String art = we.getText().replaceAll("[^0-9]+", " ");
                articules_testing.add(art);
            }
            System.out.println("Parsed page " + (i+1));


        }


        System.out.println("______________________________");
        System.out.println("______________________________");
        System.out.println("______________________________");
        System.out.println("______________________________");
        System.out.println("______________________________");
        System.out.println("______________________________");
        System.out.println("______________________________");
        System.out.println("______________________________");


        for(String str: articules_example){
            if(!articules_testing.contains(str)){
                System.out.println(str+" not found in "+url_testing);
            }

        }
        driver.close();


    }
}