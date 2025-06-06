<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>VirusTotal Analyzer</title>
        <!-- Bootstrap CSS từ CDN -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            /* Header */
            header {
                background: rgba(0, 123, 255, 0.8);
                color: white;
                text-align: center;
                padding: 2rem 1rem;
                border-radius: 10px;
                margin-bottom: 2rem;
            }

            header h1 {
                font-size: 2.5rem;
                font-weight: bold;
            }

            header p {
                font-size: 1.2rem;
                color: #dcdcdc;
            }

            /* Navigation Styling */
            .nav-tabs {
                background: #ffffff;
                border-radius: 15px;
                padding: 0.5rem;
                margin-top: -1rem;
                width: 100%;
                max-width: 900px;
                margin-left: auto;
                margin-right: auto;
            }

            .nav-tabs .nav-link {
                border-radius: 5px;
                font-weight: bold;
                color: #007bff;
                transition: background 0.3s, color 0.3s;
            }

            .nav-tabs .nav-link:hover {
                background: rgba(0, 123, 255, 0.1);
                color: #0056b3;
            }

            .nav-tabs .nav-link.active {
                background: #007bff;
                color: white !important;
            }

            /* Card Section */
            .card {
                box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
                border: none;
                border-radius: 15px;
                transition: transform 0.3s ease;
                background-color: #f9f9f9;
            }

            .card:hover {
                transform: translateY(-5px);
            }

            .card-header {
                border-top-left-radius: 15px;
                border-top-right-radius: 15px;
                background-color: #007bff;
                color: white;
            }

            /* Form Inputs */
            .form-control {
                border-radius: 10px;
                border: 1px solid #ced4da;
                transition: all 0.3s ease;
            }

            .form-control:focus {
                border-color: #007bff;
                box-shadow: 0 0 8px rgba(0, 123, 255, 0.4);
            }

            /* Button Styling */
            .btn-primary {
                background: linear-gradient(to right, #007bff, #6610f2);
                border: none;
                transition: all 0.3s ease;
                font-weight: bold;
            }

            .btn-primary:hover {
                background: linear-gradient(to right, #0056b3, #4e08c2);
                box-shadow: 0 4px 10px rgba(0, 123, 255, 0.4);
            }
        </style>
    </head>

    <body>
        <div class="container">
            <!-- Header -->
            <header>
                <h1><i class="fas fa-shield-alt"></i> VirusTotal Analyzer</h1>
                <p>Analyze URLs and files securely and efficiently</p>
            </header>

            <!-- Navigation Tabs -->
            <ul class="nav nav-tabs justify-content-center">
                <li class="nav-item">
                    <a class="nav-link active" href="/analyze/file"><i class="fas fa-file"></i> File</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/analyze/url"><i class="fas fa-link"></i> URL</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/analyze/ip"><i class="fas fa-network-wired"></i> IP</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/analyze/domain"><i class="fas fa-globe"></i> Domain</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/show">Result</a>
                </li>
            </ul>

            <!-- Form Section -->
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header text-center">
                            <h4><i class="fas fa-upload"></i> Upload File for Analysis</h4>
                        </div>
                        <div class="card-body">
                            <form action="/analyze/file" method="post" enctype="multipart/form-data">
                                <div class="form-group mb-3">
                                    <label for="file" class="form-label">Select a File:</label>
                                    <input type="file" name="file" id="file" class="form-control" required>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary w-100">
                                        <i class="fas fa-upload"></i> Upload File
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS và Popper.js từ CDN -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

    </html>