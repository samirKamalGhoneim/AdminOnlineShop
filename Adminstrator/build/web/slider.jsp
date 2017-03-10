<!DOCTYPE html>
<html>
    <title>W3.CSS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/lib/w3.css">
    <style>
        .mySlides {display:none;}
    </style>
    <body>
        <div class="w3-center">
            <span class="w3-tag w3-jumbo w3-red">S</span>
            <span class="w3-tag w3-jumbo">A</span>
            <span class="w3-tag w3-jumbo w3-yellow">L</span>
            <span class="w3-tag w3-jumbo">E</span>
        </div>
        <div class="w3-content w3-section w3-animate-bottom" style="max-width:600px;max-height: 150px">
            <div class="w3-card-4">
<<<<<<< HEAD
                <img class="mySlides" src="themes/images/products/large/1.jpg" style="width:100%;height: 320px">
            </div>
            <div class="w3-card-4">
                <img class="mySlides" src="themes/images/products/large/2.jpg" style="width:100%;height: 320px">
            </div>
            <div class="w3-card-4">
                <img class="mySlides" src="themes/images/products/large/3.jpg" style="width:100%;height: 320px">
            </div>
            <div class="w3-card-4">
                <img class="mySlides" src="themes/images/products/large/4.jpg" style="width:100%;height: 320px">
            </div>
            <div class="w3-card-4">
                <img class="mySlides" src="themes/images/products/large/10.jpg" style="width:100%;height: 320px">
=======
                <img class="mySlides" src="themes/images/products/large/1.jpg" style="width:100%;height: 260px">
            </div>
            <div class="w3-card-4">
                <img class="mySlides" src="themes/images/products/large/2.jpg" style="width:100%;height: 260px">
            </div>
            <div class="w3-card-4">
                <img class="mySlides" src="themes/images/products/large/3.jpg" style="width:100%;height: 260px">
            </div>
            <div class="w3-card-4">
                <img class="mySlides" src="themes/images/products/large/4.jpg" style="width:100%;height: 260px">
            </div>
            <div class="w3-card-4">
                <img class="mySlides" src="themes/images/products/large/10.jpg" style="width:100%;height: 260px">
>>>>>>> e020307e88d8acec8ad805ab46fc8a4c76d15110
            </div>

        </div>

        <script>
            var myIndex = 0;
            carousel();

            function carousel() {
                var i;
                var x = document.getElementsByClassName("mySlides");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                myIndex++;
                if (myIndex > x.length) {
                    myIndex = 1
                }
                x[myIndex - 1].style.display = "block";
                setTimeout(carousel, 2000); // Change image every 2 seconds
            }
        </script>

    </body>
</html>
