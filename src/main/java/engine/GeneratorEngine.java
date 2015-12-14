/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

    public GeneratorEngine(String fileName) throws IOException, Exception {
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
        constructHTML();
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
                Header.gambar = output;
//                header.setGambar(output);
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
                footer.setJumlahLink(Integer.parseInt(output));
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
//        System.out.println(gallery.getJudul());
    }

    public void constructHTML() throws FileNotFoundException, UnsupportedEncodingException, Exception {
        String output = constructHead() + constructBody();
        PrintWriter writer = new PrintWriter("index.html", "UTF-8");
        writer.println(output);
        writer.close();
    }

    public String constructHead() {
        String output = "<head>\n"
                + "    <meta charset=\"utf-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"" + meta.getTitle() + "\" content=\"width=device-width, initial-scale=1\">\n"
                + "    <meta author=\"" + meta.getAuthor() + "\" description=\"" + meta.getDescription() + "\">\n"
                + "    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n"
                + "    <link href=\"css/rplsd-dsl.css\" rel=\"stylesheet\">\n"
                + "    <link href=\"font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "</head>";
        return output;
    }

    public String constructNavigasi() {
        String mamam = "";
        if ("fixed".equals(navigasi.getPosisi())) {
            mamam = "navbar-fixed-top";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < navigasi.getJumlahMenu(); i++) {
            stringBuilder.append("                    <li>\n"
                    + "                        <a href=\"#\">Menu " + i + "</a>\n"
                    + "                    </li>");
        }
        String output = "<nav class=\"navbar navbar-inverse " + mamam + "\" role=\"navigation\">\n"
                + "        <div class=\"container\">\n"
                + "            <!-- Brand and toggle get grouped for better mobile display -->\n"
                + "            <div class=\"navbar-header\">\n"
                + "                <a class=\"navbar-brand\" href=\"index.html\">" + meta.getTitle() + "</a>\n"
                + "            </div>\n"
                + "            <!-- Collect the nav links, forms, and other content for toggling -->\n"
                + "            <div>\n"
                + "                <ul class=\"nav navbar-nav navbar-right\">\n"
                + stringBuilder.toString()
                + "                </ul>\n"
                + "            </div>\n"
                + "            <!-- /.navbar-collapse -->\n"
                + "        </div>\n"
                + "        <!-- /.container -->\n"
                + "    </nav>";
        return output;
    }

    public String urutan() throws Exception {
        String body = "";
		
		for (int i=1 ; i<=3 ; i++) {
			if (i == section.getPosisi()) {
				body += constructSection();
			} else if (i == gallery.getPosisi()) {
				body += constructGallery();
			} else if (i == carousel.getPosisi()) {
				body += constructCarousel();
			}
		}
		return body;
                
//        if (section.getPosisi() == 1 && gallery.getPosisi() == 2 && carousel.getPosisi() == 3) {
//            return constructGallery() + constructSection() + constructCarousel();
//        } else if (section.getPosisi() == 2 && gallery.getPosisi() == 1 && carousel.getPosisi() == 3) {
//            return constructGallery() + constructSection() + constructCarousel();
//        } else if (section.getPosisi() == 3 && gallery.getPosisi() == 2 && carousel.getPosisi() == 1) {
//            return constructCarousel() + constructGallery() + constructSection();
//        } else if (section.getPosisi() == 2 && gallery.getPosisi() == 3 && carousel.getPosisi() == 1) {
//            return constructCarousel() + constructSection() + constructGallery();
//        } else if (section.getPosisi() == 3 && gallery.getPosisi() == 1 && carousel.getPosisi() == 2) {
//            return constructGallery() + constructCarousel() + constructSection();
//        } else if (section.getPosisi() == 1 && gallery.getPosisi() == 3 && carousel.getPosisi() == 2) {
//            return constructSection() + constructCarousel() + constructGallery();
//        } else {
//            throw new Exception("Posisi error");
//        }

    }

    public String constructBody() throws Exception {
        return constructNavigasi() + constructHeader() + urutan() + constructFooter() + constructScript();
    }

    public String constructFooterLink() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < footer.getJumlahLink(); i++) {
            builder.append("                    <li>\n"
                    + "                            <a href=\"\">\n"
                    + "                                <img class=\"img-circle img-responsive img-center\" src=\"img/default.png\" alt=\"\">\n"
                    + "                            </a>\n"
                    + "                        </li>");
        }
        return builder.toString();
    }

    public String constructFooter() {
        String output = "<footer>\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"row\">\n"
                + "            <div class=\"col-md-12\">\n"
                + "                <div class=\"link\">\n"
                + "                    <ul>\n"
                + constructFooterLink()
                + "                    </ul>\n"
                + "                </div>\n"
                + "                <div class=\"company-name\">\n"
                + "                    <p>Copyright &copy; " + footer.getNama() + "</p>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</footer>";
        return output;
    }

    public String constructHeader() {
        String output = "	<header class=\"container\">\n"
                + "      <div class=\"row\">\n"
                + "          <div class=\"full col-md-12\" style=\"width:100%\">\n"
                + "              <h1>"+header.getJudul()+"</h1>\n"
                + "              <img src=\""+header.getGambar()+"\">\n"
                + "          </div>\n"
                + "      </div>\n"
                + "      <!-- /.row -->\n"
                + "  </header>";
        return output;
    }

    public String constructScript() {
        String output = "<!-- jQuery -->\n"
                + "    <script src=\"js/jquery.js\"></script>\n"
                + "\n"
                + "    <!-- Bootstrap Core JavaScript -->\n"
                + "    <script src=\"js/bootstrap.min.js\"></script>\n"
                + "\n"
                + "    <!-- Script to Activate the Carousel -->\n"
                + "    <script>\n"
                + "    $('.carousel').carousel({\n"
                + "        interval: 5000 //changes the speed\n"
                + "    })\n"
                + "    </script>";
        return output;
    }

    public String constructGalleryRow() {
        String output = "        <div class=\"col-md-4\">\n"
                + "            <a href=\"portfolio-item.html\">\n"
                + "                <img class=\"img-responsive img-portfolio img-hover\" src='img/default.png' alt=\"\">\n"
                + "            </a>\n"
                + "        </div>\n"
                + "        <div class=\"col-md-4\">\n"
                + "            <a href=\"portfolio-item.html\">\n"
                + "                <img class=\"img-responsive img-portfolio img-hover\" src='img/default.png' alt=\"\">\n"
                + "            </a>\n"
                + "        </div>\n"
                + "        <div class=\"col-md-4\">\n"
                + "            <a href=\"portfolio-item.html\">\n"
                + "                <img class=\"img-responsive img-portfolio img-hover\" src='img/default.png' alt=\"\">\n"
                + "            </a>\n"
                + "        </div>\n";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < gallery.getJumlahBaris(); i++) {
            stringBuilder.append(output);
        }
        return stringBuilder.toString();
    }

    public String constructGallery() {
        String output = "<section id=\"gallery\">\n"
                + "<div div=\"container\">\n"
                + "    <div class=\"row\">\n"
                + "        <div class=\"col-md-12\">\n"
                + "            <h2 class=\"page-header\">" + gallery.getJudul() + "</h2>\n"
                + "        </div>\n"
                + constructGalleryRow()
                + "    </div>  \n"
                + "</div>\n"
                + "</section>";
        return output;
    }

    public String constructSection() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h2>" + section.getJudul() + "</h2>");
        for (int i = 0; i < section.getKolom(); i++) {
            stringBuilder.append("			<div class=\"col-md-" + (12 / section.getKolom()) + "\">\n"
                    + "				<div class=\"dummyheight\">\n"
                    + "					<h1> " + section.getKolom() + " Kolom </h1>\n"
                    + "					<p>\n"
                    + "						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem neque, deleniti sunt eligendi dolore in perspiciatis maxime nemo tempora quos enim nam rerum recusandae possimus, ullam obcaecati, quis distinctio ducimus.\n"
                    + "					</p>\n"
                    + "				</div>\n"
                    + "			</div>");
        }
        String output = "<section>\n"
                + "	<div class=\"container\">\n"
                + "		<div class=\"row\">\n"
                + stringBuilder.toString()
                + "		</div>\n"
                + "	</div>\n"
                + "</section>";
        return output;
    }

    public String constructCarousel() {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < carousel.getJumlah(); i++) {
            if (i == 0) {
                builder.append("<li data-target=\"#myCarousel\" data-slide-to=\"" + i + "\" class=\"active\"></li>");
            } else {
                builder.append("        <li data-target=\"#myCarousel\" data-slide-to=\"" + i + "\"></li>\n");
            }
        }
        String dataSlide = builder.toString();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < carousel.getJumlah(); i++) {
            if (i == 0) {
                stringBuilder.append("        <div class=\"item active\">\n"
                        + "            <div class=\"fill\" style=\"background-image:url('img/default.png');\"></div>\n"
                        + "            <div class=\"carousel-caption\">\n"
                        + "                <h2>Caption " + i + "</h2>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                );
            } else {
                stringBuilder.append("        <div class=\"item\">\n"
                        + "            <div class=\"fill\" style=\"background-image:url('img/default.png');\"></div>\n"
                        + "            <div class=\"carousel-caption\">\n"
                        + "                <h2>Caption " + i + "</h2>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                );
            }
        }
        String content = stringBuilder.toString();

        String output = "<section id=\"myCarousel\" class=\"carousel slide\">\n"
                + "    <!-- Indicators -->\n"
                + "    <ol class=\"carousel-indicators\">\n"
                + dataSlide
                + "    </ol>\n"
                + "\n"
                + "    <!-- Wrapper for slides -->\n"
                + "    <div class=\"carousel-inner\">\n"
                + content
                + "    </div>\n"
                + "\n"
                + "    <!-- Controls -->\n"
                + "    <a class=\"left carousel-control\" href=\"#myCarousel\" data-slide=\"prev\">\n"
                + "        <span class=\"icon-prev\"></span>\n"
                + "    </a>\n"
                + "    <a class=\"right carousel-control\" href=\"#myCarousel\" data-slide=\"next\">\n"
                + "        <span class=\"icon-next\"></span>\n"
                + "    </a>\n"
                + "</section>";
        return output;
    }

}
