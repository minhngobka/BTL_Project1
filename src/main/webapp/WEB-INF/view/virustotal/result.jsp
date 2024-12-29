<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Kết quả phân tích</title>
            <!-- Thêm Bootstrap CSS từ CDN -->
            <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
            <style>
                /* Tùy chỉnh một số yếu tố giao diện */
                body {
                    background-color: #f8f9fa;
                }

                .card-body {
                    background-color: #ffffff;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    border-radius: 8px;
                }

                h1 {
                    color: #007bff;
                }

                pre {
                    white-space: pre-wrap;
                    word-wrap: break-word;
                    background-color: #f4f4f4;
                    padding: 15px;
                    border-radius: 5px;
                    overflow: auto;
                    /* Giới hạn chiều rộng nhưng vẫn cho phép cuộn ngang */
                    max-width: 100%;
                    box-sizing: border-box;
                }
            </style>
        </head>

        <body>
            <div class="container mt-5">
                <h1 class="text-center mb-4">Kết quả phân tích</h1>

                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Thông tin phân tích:</h5>
                        <p><strong>Loại:</strong> ${analysisResult.type}</p>
                        <p><strong>Đối tượng:</strong> ${analysisResult.target}</p>
                        <p><strong>Ket qua:</strong> ${analysisResult.result}</p>
                        <p><strong>Thoi gian:</strong> ${analysisResult.analysisTime}</p>
                        <div>
                            <!-- Hiển thị biểu đồ -->
                            <img src="${chart}" alt="Chart" />
                        </div>
                        <h5 class="mt-4">Dữ liệu JSON:</h5>
                        <div id="jsonDisplay">
                            <!-- JSON sẽ được hiển thị ở đây -->
                            <pre id="jsonContent"></pre>
                        </div>
                        <div class="mt-4 text-center">
                            <a href="/" class="btn btn-primary">Phân tích lại</a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Thêm Bootstrap JS và Popper.js từ CDN -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

            <script>
                // Lấy giá trị JSON từ server
                var jsonString = '${result}'; // Dữ liệu từ server (chuỗi JSON)

                if (jsonString && jsonString.trim()) {
                    try {
                        jsonString = jsonString
                            .replace(/\\/g, '\\\\')  // Thoát dấu gạch chéo ngược
                            .replace(/'/g, "\\'")    // Thoát dấu nháy đơn
                            .replace(/\n/g, "\\n")   // Thoát dấu xuống dòng
                            .replace(/\r/g, "\\r")   // Thoát dấu carriage return
                            .replace(/\t/g, "\\t");  // Thoát dấu tab


                        // Chuyển đổi chuỗi JSON thành đối tượng
                        var jsonObject = JSON.parse(jsonString);

                        // Hiển thị JSON theo định dạng dễ đọc
                        document.getElementById("jsonContent").textContent = JSON.stringify(jsonObject, null, 4);
                    } catch (e) {
                        // Thông báo lỗi khi không phân tích được chuỗi JSON
                        console.error("Lỗi khi phân tích JSON: ", e);
                        document.getElementById("jsonContent").innerText = "Lỗi: Không thể phân tích dữ liệu JSON.";
                    }
                } else {
                    // Thông báo khi không có dữ liệu JSON được truyền vào
                    console.error("Không có dữ liệu JSON được truyền vào.");
                    document.getElementById("jsonContent").innerText = "Không có dữ liệu JSON.";
                }
            </script>

        </body>

        </html>