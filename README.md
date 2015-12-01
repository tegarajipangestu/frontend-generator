# frontend-generator
Frontend Generator for faster prototyping and to make the world a better place


## How to Run

 0. Lakukan konfigurasi di ```input.dsl``` di project directory dan masukkan gambar sebagai headerGambar di project directory juga
 1. cd ke ```target``
 2. ```java -jar frontend-generator-1.0.jar```
 3. Frontend otomatis dibuka di default browser
 
## DSL Example 
```
GeneratorEngine.make {
    metaTitle "Frontend Template"
    metaAuthor "Jo"
    metaDescription "Lorem ipsum dolor sit amet"
    navigasiPosisi "fixed"
    navigasiJumlahMenu 5
    headerJudul "Frontend Template"
    headerGambar "image.jpg"
    sectionJudul "Judul section"
    sectionKolom 4
    sectionPosisi 3
    carouselJumlah 5
    carouselPosisi 1
    footerNama "ITB"
    footerJumlahLink 3
    galleryJudul "Judul Galeri"
    galleryJumlahBaris 3
    galleryPosisi 2
};
```


## Result
![untitled gif](https://cloud.githubusercontent.com/assets/7077388/11491007/5d3ea192-9810-11e5-87de-763db60eaa77.gif)
