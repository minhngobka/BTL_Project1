<%@page contentType="text/html" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Kết quả phân tích</title>
        <!-- Thêm Bootstrap CSS từ CDN -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Kết quả phân tích</h1>

            <div class="card">
                <div class="card-body">
                    <p>Loại: ${analysisResult.type}</p>
                    <p>Đối tượng: ${analysisResult.target}</p>
                    <p>Kết quả: ${analysisResult.result}</p>
                    <div id="jsonDisplay">
                        <!-- JSON will be displayed here -->
                    </div>
                    <a href="/" class="btn btn-secondary">Phân tích lại</a>
                </div>
            </div>
        </div>

        <!-- Thêm Bootstrap JS và Popper.js từ CDN (nếu cần tính năng như dropdowns, modals) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
            // Chuỗi JSON từ JSP
            var jsonString = '${analysisResult.result}'; // Dữ liệu từ server

            // Chuyển chuỗi thành đối tượng JSON
            var jsonObject = JSON.parse(jsonString);

            // Hiển thị JSON dưới dạng có định dạng
            document.getElementById("jsonDisplay").innerHTML = "<pre>" + JSON.stringify(jsonObject, null, 4) + "</pre>";
        </script>
    </body>

    </html>