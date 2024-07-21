<%@ page import="Model.Beans.ImageBean" %>
<%@ page import="Model.Models.ImageModel" %>
<%@ page import="Model.Models.WatchModel" %>
<%@ page import="Model.Beans.WatchBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Watchers</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/glider-js/1.7.9/glider.js" integrity="sha512-iBTjquFGC3DUyi04utYzS9qZNPVTpUkWNX2ubbbXPeD9UF86QN9M8vrPdvKydHb8qlVfzBtQnLDNPXqT40z0+A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/glider-js/1.7.9/glider.css" integrity="sha512-nfkkRjU7urjt0UPiMZpiFlK1SAy657MtPOG1DdM9kvBbwdspZ4dH+Gsu43U3Kry8UsF8eyjqjBppcw2wx7TU3w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/styleHomepage.css">
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const message = "<%=request.getParameter("message")%>";
            if("<%= request.getParameter("error")%>" === "1"){
                toastr["error"](message!==""?message:"An error occurred");
            }
            if("<%= request.getParameter("success")%>" === "1"){
                toastr["success"](message!==""?message:"Operation completed successfully");
            }
        });
    </script>
</head>
<body>
<!-- Navbar -->
<%@include file="navbar.jsp"%>

<div class="flex-container">
    <div class="flex-content">
        <!-- Contenuto della pagina -->
    </div>

<style>

    body{
        background-color: #f6f5f3;
    }

    a{
        text-decoration: none;
    }
    #mainVideo {
        position: relative;
        top: 0;
        left: 0;
        width: 100%;
        height: 70vh;
        object-fit: cover;
        z-index: -1;
    }

    #frontPage {
        position: relative;
        height: auto;
    }

    .glider-contain {
        width: 80%;
        margin: 0 auto;
    }

    .glider-prev, .glider-next {
        cursor: pointer;
        position: absolute;
        top: 50%;
        width: auto;
        padding: 16px;
        margin-top: -22px;
        color: #498e99;
        font-weight: bold;
        font-size: 18px;
        transition: 0.6s ease;

        user-select: none;
    }

    .glider-prev {
        border-radius: 3px 0 0 3px;
    }

    .glider-next{
        right: 0;
        border-radius: 0 3px 3px 0;
    }

    .glider-prev:hover, .glider-next:hover {
        background-color: #35757d;
        color: white;
    }

    .slider img {
        width: 100%;
        height: 400px;
        object-fit: contain;
    }
</style>
<div id="frontPage">
    <div class="video-text-overlay">
        <h1>TISSOT 1853</h1>
        <p> Simbolo di tradizione, precisione e raffinatezza, un tributo all'eredità storica di Tissot che dal 1853 si distingue nel panorama dell'orologeria svizzera. <br> Ogni modello della collezione Tissot 1853 incarna l'eccellenza artigianale e l'innovazione tecnologica, combinando estetica classica e funzionalità moderna.</p>
    </div>
    <video id="mainVideo" autoplay muted loop>
        <source src="homepage/home.webm" type="video/webm">
        Your browser does not support the video tag.
    </video>
</div>


<section class="about-us">
    <div class="aboutus-container">

        <h2>About Us</h2>
        <p>Benvenuti in <strong>Watchers</strong>, dove l'eleganza incontra la precisione. Siamo un'azienda dedicata a offrire orologi di lusso di altissima qualità, creati per coloro che sanno apprezzare il valore del tempo e riconoscere la propria potenzialità.</p>

        <h3>La Nostra Storia</h3>
        <p>Nata dalla passione per l'orologeria e dalla ricerca incessante della perfezione, Watchers è diventata un punto di riferimento nel mondo degli orologi di lusso. Ogni orologio che portiamo sul mercato è un tributo all'arte e all'ingegneria, unendo tradizione e innovazione in ogni singolo dettaglio.</p>

        <h3>La Nostra Missione</h3>
        <p><strong>"Watch your potential, embrace your time!"</strong> è più di un motto, è la filosofia che guida ogni nostra creazione. Crediamo che un orologio non sia solo uno strumento per misurare il tempo, ma un simbolo della propria storia, dei propri successi e delle proprie aspirazioni.</p>

        <h3>La Nostra Collezione</h3>
        <p>Ogni orologio Watchers è progettato con cura meticolosa e realizzato con i materiali più pregiati. Dalla scelta dei movimenti più precisi alla selezione dei design più raffinati, ogni dettaglio è pensato per offrirti un'esperienza senza pari. Le nostre collezioni sono un omaggio alla bellezza del tempo e alla sua inestimabile importanza nella vita di ognuno di noi.</p>

        <div class="glider-contain">
            <%
                WatchModel watchModel = new WatchModel();
                ImageModel imageModel = new ImageModel();
                List<WatchBean> watches;
            %>
            <button class="glider-prev">&#10094;</button>
            <div class="glider">
                <%
                    try {
                        watches = (List<WatchBean>) watchModel.doRetrieveByCond("WHERE visible=? AND sex=? LIMIT ?", List.of(true,"MAN",6));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    for(var watch : watches) {
                        List<ImageBean> images;
                        try {
                            images = (List<ImageBean>) imageModel.doRetrieveByCond("WHERE watch=?", List.of(watch.getId()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                %>
                <div>
                    <a href="${pageContext.request.contextPath}/watchpage/watch.jsp?id=<%=watch.getId()%>">
                        <img src="${pageContext.request.contextPath}/getImage?id=<%=images.getFirst().getId()%>&watch=<%=watch.getId()%>" alt="Image not available">
                    </a>
                </div>
                <%
                    }
                %>
            </div>
            <button class="glider-next">&#10095;</button>
        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function() {
                new Glider(document.querySelector('.glider'), {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    dots: '.dots',
                    arrows: {
                        prev: '.glider-prev',
                        next: '.glider-next'
                    },
                    responsive: [
                        {
                            breakpoint: 600,
                            settings: {
                                slidesToShow: 2,
                                slidesToScroll: 2
                            }
                        },
                        {
                            breakpoint: 900,
                            settings: {
                                slidesToShow: 3,
                                slidesToScroll: 3
                            }
                        }
                    ]
                });
            });

        </script>

        <h3>Il Nostro Impegno</h3>
        <p>Ci impegniamo a fornire non solo un prodotto di lusso, ma anche un servizio clienti eccezionale. La soddisfazione dei nostri clienti è al centro di tutto ciò che facciamo, e lavoriamo costantemente per garantire che ogni interazione con Watchers sia memorabile.</p>

        <h3>Unisciti a Noi</h3>
        <p>Indossare un Watchers significa abbracciare il proprio tempo e riconoscere il proprio potenziale. Scopri la nostra collezione e trova l'orologio che meglio riflette il tuo stile e le tue ambizioni. Perché il tempo è il bene più prezioso che abbiamo, e merita di essere vissuto con eleganza e distinzione.</p>

        <p><strong>Watchers</strong>: Watch your potential, embrace your time!</p>
    </div>
</section>

<!-- Sezione Testimonianze -->
<div class="testimonials-section">
    <div class="testimonials-inner">
        <h2>Testimonianze</h2>
        <div class="testimonial">  <p>""Ho acquistato il mio primo orologio Watchers per celebrare una promozione importante al lavoro. Non solo è un pezzo di straordinaria bellezza, ma mi ricorda ogni giorno il mio impegno e la mia dedizione. Il servizio clienti è stato impeccabile, e l'attenzione ai dettagli è evidente in ogni aspetto dell'orologio."" - Antonio Pepoli, Salerno</p> </div>
        <div class="testimonial">  <p>"Gli orologi Watchers non sono solo accessori, ma veri e propri capolavori. Ho regalato un orologio della loro collezione a mio marito per il nostro anniversario, e lui ne è rimasto incantato. Ogni volta che lo indossa, riceve complimenti per il suo design unico e raffinato." - Laura Rossi, Roma</p> </div>
        <div class="testimonial">  <p>"Essendo un appassionato di orologi di lusso, ho acquistato molti brand famosi, ma Watchers ha superato tutte le mie aspettative. La qualità del materiale e la precisione del meccanismo sono eccezionali. È evidente che Watchers mette il cuore in ogni pezzo che crea" - Alessandro Verdi, Firenze</p> </div>
    </div>
</div>

<%@include file="footer.html"%> <!-- Footer -->

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const observer = new IntersectionObserver(entries => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('fade-in');
                }
            });
        });

        const aboutUsContainer = document.querySelector('.aboutus-container');
        if (aboutUsContainer) observer.observe(aboutUsContainer);
    });
</script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
