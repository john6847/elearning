<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Header</title>
</head>

<body>
<div th:fragment="scripts">
    <script src="js/angular/services/CourseService.js"></script>
    <script src="js/angular/services/IndexService.js"></script>
    <script src="js/angular/services/CategoryService.js"></script>
    <script src="js/angular/services/cookies-service.js"></script>
    <script src="js/angular/services/login-service.js"></script>
    <script src="js/angular/controllers/LoginController.js"></script>
    <script src="js/angular/controllers/HomeController.js"></script>
    <script src="js/angular/controllers/LoginController.js"></script>
    <script src="js/angular/controllers/IndexController.js"></script>
    <script src="js/angular/controllers/CourseController.js"></script>
    <script src="js/angular/controllers/CategoryController.js"></script>
    <script src="js/angular-animate.js"></script>
    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <!-- jQuery Easing -->
    <script src="js/jquery.easing.1.3.js"></script>
    <!-- Bootstrap -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Waypoints -->
    <script src="js/jquery.waypoints.min.js"></script>
    <!-- Stellar Parallax -->
    <script src="js/jquery.stellar.min.js"></script>
    <!-- Carousel -->
    <script src="js/owl.carousel.min.js"></script>
    <!-- Flexslider -->
    <script src="js/jquery.flexslider-min.js"></script>
    <!-- countTo -->
    <script src="js/jquery.countTo.js"></script>
    <!-- Magnific Popup -->
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/magnific-popup-options.js"></script>
    <!-- Count Down -->
    <script src="js/simplyCountdown.js"></script>
    <!-- Main -->
    <script src="js/main.js"></script>
    <script>
        var d = new Date(new Date().getTime() + 1000 * 120 * 120 * 2000);

        // default example
        simplyCountdown('.simply-countdown-one', {
            year: d.getFullYear(),
            month: d.getMonth() + 1,
            day: d.getDate()
        });

        //jQuery example
        $('#simply-countdown-losange').simplyCountdown({
            year: d.getFullYear(),
            month: d.getMonth() + 1,
            day: d.getDate(),
            enableUtc: false
        });
    </script>
</div>
</body>
</html>