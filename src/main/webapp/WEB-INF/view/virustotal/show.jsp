<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Analysis Result</title>

            <!-- Bootstrap CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="/css/styles.css" rel="stylesheet" />
            <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        </head>

        <body class="bg-light d-flex justify-content-center align-items-center" style="min-height: 100vh;">
            <div class="container bg-white p-5 rounded shadow" style="max-width: 1000px; width: 100%;">

                <!-- Header -->
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h3 class="text-primary"><i class="fas fa-table"></i> Analysis Results</h3>
                    <div class="btn-group" role="group" aria-label="Export buttons">
                        <a href="/export/excel" class="btn btn-outline-success">
                            <i class="fas fa-file-excel"></i> Export Excel
                        </a>
                        <a href="/export/csv" class="btn btn-outline-success">
                            <i class="fas fa-file-csv"></i> Export CSV
                        </a>
                    </div>
                </div>
                <hr />

                <!-- Table -->
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead class="table-secondary text-center">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Type</th>
                                <th scope="col">Name</th>
                                <th scope="col">Result</th>
                                <th scope="col">Status</th>
                                <th scope="col">Analysis Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="analysisResult" items="${analysisResults}">
                                <tr>
                                    <th scope="row" class="text-center">${analysisResult.id}</th>
                                    <td>${analysisResult.type}</td>
                                    <td>${analysisResult.name}</td>
                                    <td>${analysisResult.detectionRatio}</td>
                                    <td>${analysisResult.status}</td>
                                    <td>${analysisResult.analysisTime}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Back Button -->
                <div class="text-end">
                    <a href="/" class="btn btn-primary mt-3">
                        <i class="fas fa-arrow-left"></i> Back
                    </a>
                </div>
            </div>

            <!-- Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
            <script src="/js/scripts.js"></script>
        </body>

        </html>