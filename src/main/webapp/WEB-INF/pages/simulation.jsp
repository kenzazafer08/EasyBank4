<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Easy Bank</title>
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="../../css/dashboard.css" />
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
    <div class="multi-step">
        <div class="cont">
        <form id="multi-step-form">
            <div class="form-step" data-step="1">
                <h2>Simuler mon credit</h2>
                    <div class="form-group">
                        <label for="description">Raison du prêt</label>
                        <select id="description">
                            <option value="Option 1">J'ai besoin d'argent</option>
                            <option value="Option 2">Je finance mon véhicule d'occasion</option>
                            <option value="Option 3">Je gère mes imprévus</option>
                            <option value="Option 4">J'équipe ma maison</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="amountRange">Montant (en DH)</label>
                        <div class="range-container">
                            <input type="range" id="amountRange" min="5000" max="600000" value="12000">
                            <input type="text" id="amountValue" value="12000"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="durationRange">Durée (en mois)</label>
                        <div class="range-container">
                            <input type="range" id="durationRange" min="12" max="120" value="24">
                            <input type="text" id="durationValue" value="24"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="monthlyPaymentValue">Mensualité (en DH)</label>
                        <output id="monthlyPaymentValue">500 DH par mois</output>
                    </div>

                <button class="next-step">Next</button>
            </div>
            <div class="form-step" data-step="2">
                <h2>Detail de client</h2>
                <div class="form-group">
                    <label for="clientCode">Client Code:</label>
                    <input type="text" id="clientCode">
                    <div class="client-info" id="info">
                        <div class="client-info-row">
                            <div class="client-info-label">Name:</div>
                            <div class="client-info-value" id="clientName"></div>
                        </div>
                        <div class="client-info-row">
                            <div class="client-info-label">Phone:</div>
                            <div class="client-info-value" id="clientEmail"></div>
                        </div>
                        <div class="client-info-row">
                            <div class="client-info-label">Address:</div>
                            <div class="client-info-value" id="clientPhone"></div>
                        </div>
                    </div>
                    <div style="color: red; display: none" id="clientNotFound">No client found</div>
                </div>
                <a href="/register">Create a new client</a>
                <div class="button-container">
                    <button class="prev-step">Previous</button>
                    <button class="next-step" id="next" disabled>Next</button>
                </div>
            </div>
            <div class="form-step" data-step="3">
                <h2>Confirmation</h2>
                <div class="form-group">
                    <label for="agencies">Chose an agency</label>
                    <select id="agencies">
                        <c:forEach var="agency" items="${agencies}">
                            <option value="${agency.getCode()}">${agency.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <p>Avez vous des crédits en cours ?</p>
                <div style="display: flex; justify-content: flex-start; align-items: center; padding: 2px">
                    <input type="radio" id="html" name="fav_language" value="OUI">
                    <label for="html">Oui</label><br>
                    <input type="radio" id="css" name="fav_language" value="NON">
                    <label for="css">Non</label><br>
                </div>

                <div class="button-container">
                    <button class="prev-step">Previous</button>
                    <button type="submit" class="next-step">Submit</button>
                </div>
            </div>
        </form>
        </div>
    </div>

        <div class="bottom-area">
            <div class="container">
            </div>
        </div>
    </div>
</main>


<script src="https://kit.fontawesome.com/a81368914c.js"></script>
<script src="js/script.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const next = document.getElementById("next");
        const info = document.getElementById("info");
        const clientCodeInput = document.getElementById("clientCode");
        const clientNotDisplay = document.getElementById("clientNotFound");
        const clientNameDisplay = document.getElementById("clientName");
        const clientEmailDisplay = document.getElementById("clientEmail");
        const clientPhoneDisplay = document.getElementById("clientPhone");
        clientCodeInput.addEventListener("input", function () {
            const clientCode = clientCodeInput.value;

            fetch(`/simulationSearch?code=` + clientCode)
                .then(response => response.json())
                .then(data => {
                    console.log("here");
                    console.log(data);
                    if (data.name) {
                        next.removeAttribute("disabled")
                        info.style.display = 'block';
                        clientNotDisplay.style.display = 'none';
                        clientNameDisplay.textContent = data.name;
                        clientEmailDisplay.textContent = data.phone;
                        clientPhoneDisplay.textContent = data.address;
                    } else {
                        next.setAttribute("disabled", "disabled")
                        clientNotDisplay.style.display = 'block';
                        info.style.display = 'none';
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        });
    })

    const form = document.getElementById('multi-step-form');
    const steps = Array.from(document.querySelectorAll('.form-step'));
    const prevButtons = Array.from(document.querySelectorAll('.prev-step'));
    const nextButtons = Array.from(document.querySelectorAll('.next-step'));

    let currentStep = 0;

    function showStep(stepIndex) {
        steps[currentStep].classList.remove('active');
        steps[stepIndex].classList.add('active');
        currentStep = stepIndex;
    }

    prevButtons.forEach((button, index) => {
        button.addEventListener('click', () => {
            if (currentStep > 0) {
                showStep(currentStep - 1);
            }
        });
    });

    nextButtons.forEach((button, index) => {
        button.addEventListener('click', () => {
            if (currentStep < steps.length - 1) {
                showStep(currentStep + 1);
            }
        });
    });

    form.addEventListener('submit', (e) => {
        e.preventDefault();
    });

    showStep(currentStep);

    const amountRange = document.getElementById("amountRange");
    const amountValue = document.getElementById("amountValue");
    const durationRange = document.getElementById("durationRange");
    const durationValue = document.getElementById("durationValue");
    const monthlyPaymentValue = document.getElementById("monthlyPaymentValue");

    function updateTextInput(inputRange, inputText) {
        inputText.value = inputRange.value;
    }
    function updateRangeInput(inputText, inputRange) {
        const value = parseInt(inputText.value);
        if (value >= parseInt(inputRange.min) && value <= parseInt(inputRange.max)) {
            inputRange.value = value;
        }
    }
    function updateMonthlyPayment() {
        const amount = parseInt(amountRange.value);
        const duration = parseInt(durationRange.value);

        const monthlyPayment = calculateMonthlyPayment(amount, duration);

        monthlyPaymentValue.textContent = monthlyPayment + " DH par mois";

        amountValue.value = amount;
        durationValue.value = duration;
    }
    function calculateMonthlyPayment(amount, duration) {
        const annualPercentageRate = 4/* set your annual percentage rate here */;

        const monthlyRate = (annualPercentageRate / 100) / 12;

        const numerator = amount * monthlyRate;
        const denominator = 1 - Math.pow(1 + monthlyRate, -duration);
        const monthlyPayment = numerator / denominator;

        return monthlyPayment.toFixed(2); // Format to two decimal places
    }


    amountRange.addEventListener("input", () => {
        updateTextInput(amountRange, amountValue);
        updateMonthlyPayment();

    });

    durationRange.addEventListener("input", () => {
        updateTextInput(durationRange, durationValue);
        updateMonthlyPayment();
    });

    amountValue.addEventListener("input", () => {
        updateRangeInput(amountValue, amountRange);
        updateMonthlyPayment();
    });

    durationValue.addEventListener("input", () => {
        updateRangeInput(durationValue, durationRange);
        updateMonthlyPayment();
    });

</script>
</body>
</html>
