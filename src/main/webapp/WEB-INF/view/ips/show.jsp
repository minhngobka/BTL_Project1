<%@page contentType="text/html" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>VirusTotal Analyzer</title>
        <!-- Thêm Bootstrap CSS từ CDN -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="container mt-5">
            <jsp:include page="../layout/header.jsp" />

            <div class="container">
                <jsp:include page="../layout/navigation.jsp" />
                <form action="/analyze/ip" method="post">
                    <div class="form-group">
                        <label for="target">Nhập dia chi ip:</label>
                        <input type="text" id="target" name="target" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Phân tích</button>
                </form>
            </div>
        </div>

        <!-- Thêm Bootstrap JS và Popper.js từ CDN (nếu cần tính năng như dropdowns, modals) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

    </html>