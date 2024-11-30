<%@page contentType="text/html" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body class="d-flex flex-column justify-content-center align-items-center min-vh-100 bg-light">
        <div class="text-center">
            <div class="alert alert-danger" role="alert">
                <h1 class="display-4">Oops! Something went wrong</h1>
                <p class="lead">We encountered an unexpected error. Please try again later.</p>
            </div>
            <a href="/" class="btn btn-primary mt-3">
                <i class="bi bi-arrow-left"></i> Back to Home
            </a>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>