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
                        <li><a href="#">
                            <i class="fas fa-chart-bar"></i>
                            <span class="nav-item">Simulations</span>
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
                    <h1>Employees</h1>

                    <div class="search_bar">
                        <div class="search_group">
                            <input type="search" id="search" placeholder="Chercher un employee ici...">
                            <div class="cta">
                                <a href="#" class="btn" onclick="searchClient()"><i class="fas fa-search"></i></a>
                            </div>
                        </div>
                        <div class="cta">
                            <a href="#" class="btn" onclick="addClient()"><i class="fas fa-plus"></i></a>
                        </div>
                    </div>
                    <table id="keywords" cellspacing="0" cellpadding="0" style="margin-top: 30px;">
                        <thead>
                        <tr>
                            <th><span>Code</span></th>
                            <th><span>First name</span></th>
                            <th><span>Last name</span></th>
                            <th><span>Phone number</span></th>
                            <th><span>Address</span></th>
                            <th><span>Actions</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty employee}">
                                <tr>
                                    <td>${employee.getNumber()}</td>
                                    <td>${employee.getFirstName()}</td>
                                    <td>${employee.getLastName()}</td>
                                    <td>${employee.getPhone()}</td>
                                    <td>${employee.getAddress()}</td>
                                    <td style="display: flex; justify-content: space-between;">
                                        <div class="cta" style="margin-right: 10px">
                                            <a href="#" onclick="updateClient('${employee.getNumber()}', '${employee.getFirstName()}', '${employee.getLastName()}',  '${employee.getPhone()}', '${employee.getAddress()}')" class="btn"><i class="fas fa-edit"></i></a>
                                        </div>
                                        <div class="cta">
                                            <a href="#" class="btn" onclick="confirmDelete('${employee.getNumber()}', '${employee.getFirstName()}')" style="background-color: #9d3f3f"><i class="fas fa-trash"></i></a>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${noEmployeeFound or invalidEmployeeId}">
                                <tr>
                                    <td colspan="5">No Employee with this number.</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${not empty requestScope.employees}">
                                    <%--@elvariable id="employees" type="java.util.List"--%>
                                    <c:forEach var="employee" items="${employees}">
                                        <tr>
                                            <td class="lalign">${employee.getNumber()}</td>
                                            <td>${employee.getFirstName()}</td>
                                            <td>${employee.getLastName()}</td>
                                            <td>${employee.getPhone()}</td>
                                            <td>${employee.getAddress()}</td>
                                            <td style="display: flex; justify-content: space-between;">
                                                <div class="cta" style="margin-right: 10px">
                                                    <a href="#" onclick="updateClient('${employee.getNumber()}', '${employee.getFirstName()}', '${employee.getLastName()}',  '${employee.getPhone()}', '${employee.getAddress()}')" class="btn"><i class="fas fa-edit"></i></a>
                                                </div>
                                                <div class="cta">
                                                    <a href="#" class="btn" onclick="confirmDelete('${employee.getNumber()}', '${employee.getFirstName()}')" style="background-color: #9d3f3f"><i class="fas fa-trash"></i></a>
                                                </div>
                                            </td>
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
    function searchClient() {
        var clientId = document.getElementById("search").value;
        let url = "<%= request.getContextPath()%>/Employee?code=" + clientId;
        console.log(url);
        window.location.href = url;
    }
    function confirmDelete(clientCode, clientName) {
        swal.fire({
            title: 'Suppression employee',
            text: 'Voulez vous vraiment supprimer cet employee de nom '+ clientName +' ?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Oui!',
            cancelButtonText: 'Non',
        }).then((result) => {
            if (result.isConfirmed) {
                let url = "/deleteEmployee?code=" + clientCode;
                fetch(url, {
                    method: 'POST',
                }).then(response => {
                    if (response.status === 404) {
                        response.json().then(data => {
                            swal.fire(data.message);
                        });
                    } else {
                        swal.fire("Employee supprimé!")
                            .then(() => {
                                location.reload();
                            });
                    }
                }).catch(error => {
                    console.error('Error:', error);
                });

            }
        })
    }
    function addClient() {
        console.log('here')
        swal.fire({
            title: 'Ajouter un nouveau client',
            html:
                '<input id="swal-input-firstName" class="swal2-input" placeholder="Prénom">' +
                '<input id="swal-input-lastName" class="swal2-input" placeholder="Nom de famille">' +
                '<input id="swal-input-phoneNumber" class="swal2-input" placeholder="Numéro de téléphone">'+
                '<input id="swal-input-address" class="swal2-input" placeholder="Adresse">',
            showCancelButton: true,
            confirmButtonText: 'Ajouter',
            cancelButtonText: 'Annuler',
            showLoaderOnConfirm: true,
            preConfirm: () => {
                const firstName = document.getElementById('swal-input-firstName').value;
                const lastName = document.getElementById('swal-input-lastName').value;
                const phoneNumber = document.getElementById('swal-input-phoneNumber').value;
                const address = document.getElementById('swal-input-address').value;

                return fetch("/registerEmployee?firstName="+ firstName +"&lastName="+ lastName +"&phoneNumber="+ phoneNumber +"&address=" + address, {
                    method: 'POST'
                })
                    .then(response => {
                        if (response.status === 404) {
                            response.json().then(data => {
                                swal.fire(data.message);
                            });
                        } else {
                            swal.fire("Employee ajouté!")
                                .then(() => {
                                    location.reload();
                                });
                        }
                    })
                    .catch(error => {
                        swal.showValidationMessage(`Une erreur s'est produite: ` + error);
                    });
            },
            allowOutsideClick: () => !swal.isLoading()
        });
    }
    function updateClient(number, fn, ln , pn, ad) {
        swal.fire({
            title: 'Mettre à jour le client',
            html:
                '<input id="swal-input-firstName" class="swal2-input" value=' + fn +'>' +
                '<input id="swal-input-lastName" class="swal2-input" value=' + ln +'>' +
                '<input id="swal-input-phoneNumber" class="swal2-input" value=' + pn +'>' +
                '<input id="swal-input-address" class="swal2-input" value=' + ad +'>',
            showCancelButton: true,
            confirmButtonText: 'Mettre à jour',
            cancelButtonText: 'Annuler',
            showLoaderOnConfirm: true,
            preConfirm: () => {
                const firstName = document.getElementById('swal-input-firstName').value;
                const lastName = document.getElementById('swal-input-lastName').value;
                const phoneNumber = document.getElementById('swal-input-phoneNumber').value;
                const address = document.getElementById('swal-input-address').value;

                return fetch("/editEmployee?code=" + number + "&firstName=" + firstName + "&lastName=" + lastName + "&phoneNumber=" + phoneNumber + "&address=" + address , {
                    method: 'POST'
                })
                    .then(response => {
                        if (response.status === 404) {
                            response.json().then(data => {
                                swal.fire(data.message);
                            });
                        } else {
                            swal.fire("Employee mis à jour!")
                                .then(() => {
                                    location.reload();
                                });
                        }
                    })
                    .catch(error => {
                        swal.showValidationMessage(`Une erreur s'est produite: ` + error);
                    });
            },
            allowOutsideClick: () => !swal.isLoading()
        });
    }
</script>
</body>
</html>