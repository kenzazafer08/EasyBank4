<%@ page import="com.example.easybank4.dto.Client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Easy Bank</title>
    <link rel="stylesheet" href="../../css/style.css" />
    <link rel="stylesheet" href="../../css/dashboard.css" />
</head>
<body>

<main>
    <div class="big-wrapper light">
        <img src="../../img/shape.png" alt="" class="shape" />

        <header>
            <div class="container">
                <div class="logo">
                    <img src="../../img/logo.png" alt="Logo" />
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



        <div class="container" style="margin-top: 100px;">
            <nav>
                <div class="navbar">
                    <ul style="padding-top : 30px">
                        <li><a href="<%= request.getContextPath()%>/clients">
                            <i class="fas fa-user"></i>
                            <span class="nav-item">Clients</span>
                        </a>
                        </li>
                        <li><a href="<%= request.getContextPath()%>/employees">
                            <i class="fas fa-tasks"></i>
                            <span class="nav-item">Employees</span>
                        </a>
                        </li>
                        <li><a href="<%= request.getContextPath()%>/agencies">
                            <i class="fab fa-dochub"></i>
                            <span class="nav-item">Agencies</span>
                        </a>
                        </li>
                        <li><a href="<%= request.getContextPath()%>/credits">
                            <i class="fas fa-chart-bar"></i>
                            <span class="nav-item">Credits</span>
                        </a>
                        </li>
                        <li><a href="#" class="logout">
                            <i class="fas fa-sign-out-alt"></i>
                            <span class="nav-item">Logout</span>
                        </a>
                        </li>
                    </ul>
                </div>
            </nav>

            <section class="main">
                <div class="main-body">
                    <h1>Agencies</h1>

                    <div class="search_bar">
                        <div class="search_group">
                            <input type="search" id="search" placeholder="Chercher un credit ici...">
                            <div class="cta">
                                <a href="#" class="btn" onclick="searchCredit()"><i class="fas fa-search"></i></a>
                            </div>
                        </div>
                    </div>
                    <table id="keywords" cellspacing="0" cellpadding="0" style="margin-top: 30px; max-width: 60%">
                        <thead>
                        <tr>
                            <th><span>Code</span></th>
                            <th><span>Amount</span></th>
                            <th><span>Duration</span></th>
                            <th><span>Monthly</span></th>
                            <th><span>Client</span></th>
                            <th><span>Agency</span></th>
                            <th><span>Employee</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty credit}">
                                <tr>
                                    <td>${credit.getCode()}</td>
                                    <td>${credit.getAmount()}</td>
                                    <td>${credit.getDuration()}</td>
                                    <td>${credit.getMonthly()}</td>
                                    <td>${credit.getClient().getCode()}</td>
                                    <td>${credit.getAgency().getCode()}</td>
                                    <td>${credit.getEmployee().getNumber()}</td>
                                </tr>
                            </c:when>
                            <c:when test="${noCreditFound or invalidCreditId}">
                                <tr>
                                    <td colspan="5">No Credit with this code.</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${not empty requestScope.credits}">
                                    <%--@elvariable id="clients" type="java.util.List"--%>
                                    <c:forEach var="credit" items="${requestScope.credits}">
                                        <tr>
                                            <td>${credit.getCode()}</td>
                                            <td>${credit.getAmount()}</td>
                                            <td>${credit.getDuration()}</td>
                                            <td>${credit.getMonthly()}</td>
                                            <td>${credit.getClient().getCode()}</td>
                                            <td>${credit.getAgency().getCode()}</td>
                                            <td>${credit.getEmployee().getNumber()}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>

                </div>
            </section>
        </div>



    </div>

</main>

<!-- JavaScript Files -->

<script src="https://kit.fontawesome.com/a81368914c.js"></script>
<script src="../../js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function searchCredit() {
        var agencyId = document.getElementById("search").value;
        let url = "<%= request.getContextPath()%>/credit?code=" + agencyId;
        console.log(url);
        window.location.href = url;
    }
</script>
</body>
</html>