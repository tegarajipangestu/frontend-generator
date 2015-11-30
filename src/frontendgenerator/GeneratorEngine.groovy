/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontendgenerator

/**
 *
 * @author tegarnization
 */
import frontendgenerator.model.*
import frontendgenerator.model.Carousel
import frontendgenerator.model.Footer
import frontendgenerator.model.Gallery
import frontendgenerator.model.Header
import frontendgenerator.model.Meta
import frontendgenerator.model.Navigasi
import frontendgenerator.model.Section
import groovy.xml.MarkupBuilder

/**
 * Created by rikysamuel on 11/27/2015.
 */
class GeneratorEngine {
    
    Meta meta = new Meta();
    Carousel carousel = new Carousel();
    Gallery gallery = new Gallery();
    Header header = new Header();
    Navigasi navigasi = new Navigasi();
    Section section = new Section();
    Footer footer = new Footer();
    
    def static writer = new StringWriter()

    
    def static make(closure) {
        GeneratorEngine generatorEngine = new GeneratorEngine()
        closure.delegate = generatorEngine
        closure()
    }


    def metaTitle (String title) {
        meta.setTitle(title);
    }
    
    def metaAuthor (String author) {
        meta.setAuthor(author);
    }
    
    def metaDescription (String description) {
        meta.setDescription(description);
    }
    
    def navigasiPosisi (String posisi) {
        navigasi.setPosisi(posisi);
    }
    
    def navigasiJumlahMenu (int jumlahMenu) {
        navigasi.setJumlahMenu(jumlahMenu);
    }
    
    def headerJudul (String judul) {
        header.setJudul(judul);
    }
    
    def headerGambar (String gambar) {
        header.setGambar(gambar);
    }
    
    def sectionJudul (String judul) {
        section.setJudul(judul);
    }
    
    def sectionKolom (int kolom) {
        section.setKolom(kolom);
    }
    
    def sectionPosisi (int posisi) {
        section.setPosisi(posisi);
    }
    
    def carouselJumlah (int jumlah) {
        carousel.setJumlah(jumlah);
    }
    
    def carouselPosisi (int posisi) {
        carousel.setPosisi(posisi);
    }
    
    def footerNama (String nama) {
        footer.setNama(nama);
    }
    
    def footerJumlahLink (String jumlahLink) {
        footer.setJumlahLink(jumlahLink);
    }
    
    def galleryJudul (String judul) {
        gallery.setJudul(judul);
    }
    
    def galleryJumlahBaris (int jumlahBaris) {
        gallery.setJumlahBaris(jumlahBaris);
    }
    
    def galleryPosisi (int posisi) {
        gallery.setPosisi(posisi);
    }

    def methodMissing(String methodName, args) {
        println "Wrong Syntax"
    }
    
    def gettemplateTwo() {
        if (name != null) {
            if (description != null) {
                if (profilePicPath != null) {
                    if (parentLocation != null) {
                        if (fileName != null) {
                            doTemplateTwo(this)
                        } else {
                            println "output name is not defined"
                        }
                    } else {
                        println "directory location is not defined"
                    }
                } else {
                    println "profile picture path is not defined!"
                }
            } else {
                println "description is not defined!"
            }
        } else {
            println "name is not defined!"
        }
    }

    def static templateOne(GeneratorEngine htmlDsl) {
        def markup = new MarkupBuilder(writer)

        markup.html() {
            head {
                title(htmlDsl.name)
                meta(charset: "utf-8")
                link(href: "css/style1.css", rel:"stylesheet", type:"text/css", charset: "utf-8")
            }
            body {
                span(id: "background")
                div id: "page", {
                    div id: "sidebar", {
                        div id: "title", {
                            mkp.yield htmlDsl.name
                        }
                        ul id: "profile", {
                            li {
                                mkp.yield htmlDsl.description
                            }
                        }

                    }

                    div id: "contents", {
                        ul class: "images", {
                            li {
                                img(src: "images/illustration1.jpg", alt: "")
                                p {
                                    mkp.yield "Lorem Ipsum"
                                }
                            }
                            li {
                                img(src: "images/illustration2.jpg", alt:"")
                                p {
                                    mkp.yield "tes"
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    def static templateTwo(GeneratorEngine htmlDsl) {
        def markup = new MarkupBuilder(writer)

        markup.html() {
            head {
                title(htmlDsl.name)
                meta(charset: "utf-8")
                link(href: "css/style.css", rel: "stylesheet", type: "text/css", charset: "utf-8")
            }
            body {
                div class: "lightbox", {
                    div class: "center", {
                        img(class: "fullimg", src: "", alt: "")
                        div class: "fulldesc", {
                            mkp.yield ""
                        }
                    }
                }

                main class: "page", {
                    header class: "title", {
                        img(src: "avatar.jpg", alt: "logo")
                        if (htmlDsl.color == null) {
                            h1 {
                                mkp.yield htmlDsl.name
                            }
                        } else {
                            h1 style: "color:" + htmlDsl.color + ";", {
                                mkp.yield htmlDsl.name
                            }
                        }
                        h2 {
                            mkp.yield htmlDsl.description
                            a href: "php/upload.php", {
                                mkp.yield "Upload"
                            }
                        }
                    }
                    div class: "contents", {
                        mkp.yieldUnescaped "\n"
                        mkp.yieldUnescaped indexPhpCode()

                        div class: "clearfix", {
                            mkp.yield ""
                        }
                    }

                    footer {
                        p{
                            mkp.yield htmlDsl.footer
                        }
                    }
                }
            }
            script(src: "jquery/jquery.min.js") {
                mkp.yield ""
            }
            script(src: "jquery/script.js") {
                mkp.yield ""
            }
        }
    }

    private static doTemplateTwo(GeneratorEngine htmlDsl) {
        if (!new File(htmlDsl.parentLocation).exists()) {
            new File(htmlDsl.parentLocation).mkdirs();
        }

        templateTwo(htmlDsl)

        File directory = new File(htmlDsl.parentLocation);
        if (!directory.exists()) {
            directory.mkdirs()
        }

        CopyWebDirectories.createAvatar(htmlDsl.profilePicPath, htmlDsl.parentLocation)
        CopyWebDirectories.moveFileIntoOutFolder("resources", htmlDsl.parentLocation)

        File file = new File(htmlDsl.parentLocation, htmlDsl.fileName);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }

        file << writer;
    }
    
    
    def getCoba() {
        
        //        File directory = new File("/home/test");
        //        if (!directory.exists()) {
        //            directory.mkdirs()
        //        }

        //        CopyWebDirectories.createAvatar(htmlDsl.profilePicPath, htmlDsl.parentLocation)
        //        CopyWebDirectories.moveFileIntoOutFolder("resources", htmlDsl.parentLocation)


        
//        File file = new File("/home/cuk", "jancuk.txt");
//        if (file.exists()) {
//            file.delete();
//            file.createNewFile();
//        }
//
//        file << writer;
        System.out.println("berhasil cuks");
    }
    
    public static void main(String[] args) {
        GeneratorEngine.make {
            metaTitle "Template Jancuk"
            metaAuthor "Bagas"
            metaDescription "Ini template cuk"
            navigasiPosisi "fixed"
            navigasiJumlahMenu 3
            headerJudul "Judul Jancuk"
            headerGambar "jancuk.jpg"
            sectionJudul "Judul section cuk"
            sectionKolom 3
            sectionPosisi 2
            carouselJumlah 3
            carouselPosisi 1
            footerNama "Perusahaan Jancuk"
            footerJumlahLink 3
            galleryJudul "Judul Galeri cuk"
            galleryJumlahBaris 3
            galleryPosisi 3
            coba
        };
    }

}
