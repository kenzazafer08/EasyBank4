<%@ page import="com.example.solution_jee.model.Client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: YC
  Date: 12/10/2023
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
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

<% String no_clients_found = (String) request.getAttribute("no_clients_found");
    Client client = (Client) request.getAttribute("client1");%>

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
                            <li><a href="../../index.jsp">Acceuil</a></li>
                            <li><a href="/clients" class="btn">Dashboard</a></li>
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
                            <li><a href="#">
                                <i class="fas fa-user"></i>
                                <span class="nav-item">Dashboard</span>
                            </a>
                            </li>
                            <li><a href="#">
                                <i class="fas fa-chart-bar"></i>
                                <span class="nav-item">Analytics</span>
                            </a>
                            </li>
                            <li><a href="#">
                                <i class="fas fa-tasks"></i>
                                <span class="nav-item">Jobs Board</span>
                            </a>
                            </li>
                            <li><a href="#">
                                <i class="fab fa-dochub"></i>
                                <span class="nav-item">Documnents</span>
                            </a>
                            </li>
                            <li><a href="#">
                                <i class="fas fa-cog"></i>
                                <span class="nav-item">Setting</span>
                            </a>
                            </li>
                            <li><a href="#">
                                <i class="fas fa-question-circle"></i>
                                <span class="nav-item">Help</span>
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
                        <h1>Clients</h1>

                        <div class="search_bar">
                            <div class="search_group">
                                <input type="search" id="search" placeholder="Chercher un client ici...">
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
                                <th><span>Birth date</span></th>
                                <th><span>Phone number</span></th>
                                <th><span>Address</span></th>
                            </tr>
                            </thead>
                            <tbody>


                                    <% if (no_clients_found == null || no_clients_found.isEmpty()) { %>
                                        <c:forEach var="client" items="${clients}">
                                            <tr>
                                                <td class="lalign">${client.get_code()}</td>
                                                <td>${client.get_firstName()}</td>
                                                <td>${client.get_lastName()}</td>
                                                <td>${client.get_birthDate()}</td>
                                                <td>${client.get_phoneNumber()}</td>
                                                <td>${client.get_address()}</td>
                                                <td style="display: flex; justify-content: space-between;">
                                                    <div class="cta" style="margin-right: 10px">
                                                        <a href="#" onclick="updateClient('${client.get_code()}', '${client.get_firstName()}', '${client.get_lastName()}', '${client.get_birthDate()}', '${client.get_phoneNumber()}', '${client.get_address()}')" class="btn"><i class="fas fa-edit"></i></a>
                                                    </div>
                                                    <div class="cta">
                                                        <a href="#" class="btn" onclick="confirmDelete('${client.get_code()}', '${client.get_firstName()}')" style="background-color: #ff0000"><i class="fas fa-trash"></i></a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    <% } %>
                                    <%if (client != null) {%>
                                            <tr>
                                                <td class="lalign"><%= client.get_code() %></td>
                                                <td><%=client.get_firstName()%></td>
                                                <td><%=client.get_lastName()%></td>
                                                <td><%=client.get_birthDate()%></td>
                                                <td><%=client.get_phoneNumber()%></td>
                                                <td><%=client.get_address()%></td>
                                                <td style="display: flex; justify-content: space-between;">
                                                    <div class="cta" style="margin-right: 10px">
                                                        <a href="/clients" class="btn"><i class="fas fa-edit"></i></a>
                                                    </div>
                                                    <div class="cta">
                                                        <a href="/clients" class="btn" style="background-color: #ff0000"><i class="fas fa-trash"></i></a>
                                                    </div>
                                                </td>
                                            </tr>
                                    <%}%>

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
            let url = "/clients?action=search&code=" + clientId;
            console.log(url);
            window.location.href = url;
        }
        function confirmDelete(clientCode, clientName) {
            swal.fire({
                title: 'Suppression client',
                text: 'Voulez vous vraiment supprimer ce client de nom '+ clientName +' ?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Oui!',
                cancelButtonText: 'Non',
            }).then((result) => {
                if (result.isConfirmed) {
                    let url = "/clients?action=delete&code=" + clientCode;
                    fetch(url, {
                        method: 'DELETE',
                    }).then(response => {
                        if (response.status === 404) {
                            response.json().then(data => {
                                swal.fire(data.message);
                            });
                        } else {
                            swal.fire("Client supprimé!")
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
                    '<input id="swal-input-birthDate" type="date" class="swal2-input" placeholder="Date de naissance">' +
                    '<input id="swal-input-phoneNumber" class="swal2-input" placeholder="Numéro de téléphone">'+
                    '<input id="swal-input-address" class="swal2-input" placeholder="Adresse">',
                showCancelButton: true,
                confirmButtonText: 'Ajouter',
                cancelButtonText: 'Annuler',
                showLoaderOnConfirm: true,
                preConfirm: () => {
                    const firstName = document.getElementById('swal-input-firstName').value;
                    const lastName = document.getElementById('swal-input-lastName').value;
                    const birthDate = document.getElementById('swal-input-birthDate').value;
                    const phoneNumber = document.getElementById('swal-input-phoneNumber').value;
                    const address = document.getElementById('swal-input-address').value;

                    return fetch("/clients?action=create&firstName="+ firstName +"&lastName="+ lastName +"&birthDate="+ birthDate +"&phoneNumber="+ phoneNumber +"&address=" + address, {
                        method: 'POST'
                    })
                        .then(response => {
                            if (response.status === 404) {
                                response.json().then(data => {
                                    swal.fire(data.message);
                                });
                            } else {
                                swal.fire("Client ajouté!")
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

        function updateClient(clientCode, fn, ln, bd, pn, ad) {
            swal.fire({
                title: 'Mettre à jour le client',
                html:
                    '<input id="swal-input-firstName" class="swal2-input" value=' + fn +'>' +
                    '<input id="swal-input-lastName" class="swal2-input" value=' + ln +'>' +
                    '<input id="swal-input-birthDate" type="date" class="swal2-input" value=' + bd +'>' +
                    '<input id="swal-input-phoneNumber" class="swal2-input" value=' + pn +'>' +
                    '<input id="swal-input-address" class="swal2-input" value=' + ad +'>',
                showCancelButton: true,
                confirmButtonText: 'Mettre à jour',
                cancelButtonText: 'Annuler',
                showLoaderOnConfirm: true,
                preConfirm: () => {
                    const firstName = document.getElementById('swal-input-firstName').value;
                    const lastName = document.getElementById('swal-input-lastName').value;
                    const birthDate = document.getElementById('swal-input-birthDate').value;
                    const phoneNumber = document.getElementById('swal-input-phoneNumber').value;
                    const address = document.getElementById('swal-input-address').value;

                    return fetch("/clients?action=update&code=" + clientCode + "&firstName=" + firstName + "&lastName=" + lastName + "&birthDate=" + birthDate + "&phoneNumber=" + phoneNumber + "&address=" + address, {
                        method: 'PUT'
                    })
                        .then(response => {
                            if (response.status === 404) {
                                response.json().then(data => {
                                    swal.fire(data.message);
                                });
                            } else {
                                swal.fire("Client mis à jour!")
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
