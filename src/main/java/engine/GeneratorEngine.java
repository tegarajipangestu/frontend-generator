/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.IOException;
import model.Carousel;
import model.Footer;
import model.Gallery;
import model.Header;
import model.Meta;
import model.Navigasi;
import model.Section;
import reader.DSLReader;

/**
 *
 * @author tegarnization
 */
public class GeneratorEngine {

    private final DSLReader dSLReader;
    private final int numProperty;
    Meta meta;
    Carousel carousel;
    Gallery gallery;
    Header header;
    Navigasi navigasi;
    Section section;
    Footer footer;

    private enum propertyVarEnum {
        metaTitle,
        metaAuthor,
        metaDescription,
        navigasiPosisi,
        navigasiJumlahMenu,
        headerJudul,
        headerGambar,
        sectionJudul,
        sectionKolom,
        sectionPosisi,
        carouselJumlah,
        carouselPosisi,
        footerNama,
        footerJumlahLink,
        galleryJudul,
        galleryJumlahBaris,
        galleryPosisi
    };

    public GeneratorEngine(String fileName) throws IOException {
        this.numProperty = 7;
        dSLReader = new DSLReader(fileName);
        meta = new Meta();
        carousel = new Carousel();
        gallery = new Gallery();
        header = new Header();
        navigasi = new Navigasi();
        section = new Section();
        footer = new Footer();

        try {
            readAllProperty(fileName);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void readEachProperty(String input) throws Exception {
        for (propertyVarEnum prop : propertyVarEnum.values()) {
            String output = dSLReader.readProperty(prop.name());
            if ("metaTitle".equals(prop.name())) {
                meta.setTitle(output);
            } else if ("metaAuthor".equals(prop.name())) {
                meta.setAuthor(output);
            } else if ("metaDescription".equals(prop.name())) {
                meta.setDescription(output);
            } else if ("navigasiPosisi".equals(prop.name())) {
                navigasi.setPosisi(output);
            } else if ("navigasiJumlahMenu".equals(prop.name())) {
                navigasi.setJumlahMenu(Integer.parseInt(output));
            } else if ("headerJudul".equals(prop.name())) {
                header.setJudul(output);
            } else if ("headerGambar".equals(prop.name())) {
                header.setGambar(output);
            } else if ("sectionJudul".equals(prop.name())) {
                section.setJudul(output);
            } else if ("sectionKolom".equals(prop.name())) {
                section.setKolom(Integer.parseInt(output));
            } else if ("sectionPosisi".equals(prop.name())) {
                section.setPosisi(Integer.parseInt(output));
            } else if ("carouselJumlah".equals(prop.name())) {
                carousel.setJumlah(Integer.parseInt(output));
            } else if ("carouselPosisi".equals(prop.name())) {
                carousel.setPosisi(Integer.parseInt(output));
            } else if ("footerNama".equals(prop.name())) {
                footer.setNama(output);
            } else if ("footerJumlahLink".equals(prop.name())) {
                footer.setJumlahLink(output);
            } else if ("galleryJudul".equals(prop.name())) {
                gallery.setJudul(output);
            } else if ("galleryJumlahBaris".equals(prop.name())) {
                gallery.setJumlahBaris(Integer.parseInt(output));
            } else if ("galleryPosisi".equals(prop.name())) {
                gallery.setPosisi(Integer.parseInt(output));
            }
        }
    }

    public void readAllProperty(String input) throws Exception {
        readEachProperty(input);
        System.out.println(gallery.getJudul());
    }

    public void generateHTML() {

    }

    public String constructHTML() {
        return constructHead() + constructBody();
    }

    public String constructHead() {
        String output = "<head>\n"
                + "    <meta charset=\"utf-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\""+meta.getTitle()+"\" content=\"width=device-width, initial-scale=1\">\n"
                + "    <meta author=\""+meta.getAuthor()+"\" description=\""+meta.getDescription()+"\">\n"
                + "    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n"
                + "    <link href=\"css/rplsd-dsl.css\" rel=\"stylesheet\">\n"
                + "    <link href=\"font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "</head>";
        return output;
    }
    
    public String constructBody() {
        return null;
    }

}
