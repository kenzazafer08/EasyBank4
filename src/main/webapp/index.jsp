<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Easy Bank</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<main>
    <div class="big-wrapper light">
        <img src="img/shape.png" alt="" class="shape" />

        <header>
            <div class="container">
                <div class="logo">
                    <img src="./img/logo.png" alt="Logo" />
                    <h3>EASY BANK</h3>
                </div>

                <div class="links">
                    <ul>
                        <li><a href="/index.jsp">Acceuil</a></li>
                        <li><a href="<%= request.getContextPath()%>/simulation" >Simulation</a></li>
                        <li><a href="<%= request.getContextPath()%>/clients" class="btn">Dashboard</a></li>
                    </ul>
                </div>

                <div class="overlay"></div>

                <div class="hamburger-menu">
                    <div class="bar"></div>
                </div>
            </div>
        </header>

        <div class="showcase-area">
            <div class="container">
                <div class="left">
                    <div class="big-title">
                        <h1>EasyBank bank,</h1>
                        <h1>Votre banque digitale de future.</h1>
                    </div>
                    <p class="text">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                        Delectus eius distinctio odit, magni magnam qui ex perferendis
                        vitae!
                    </p>
                    <div class="cta">
                        <a href="/clients" class="btn">Voir le dashboard</a>
                    </div>
                </div>

                <div class="right">
                    <img src="./img/person.png" alt="Person Image" class="person" />
                </div>
            </div>
        </div>

        <div class="bottom-area">
            <div class="container">
                <!--<button class="toggle-btn">
                    <i class="far fa-moon"></i>
                    <i class="far fa-sun"></i>
                </button>-->
            </div>
        </div>
    </div>
</main>

<!-- JavaScript Files -->

<script src="https://kit.fontawesome.com/a81368914c.js"></script>
<script src="js/script.js"></script>
</body>
</html>