<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .video-container {
            width: 100%;
            max-width: 800px; /* Optional: limits the maximum width */
            margin: 0 auto;   /* Centers the video */
            padding: 15px;
            box-sizing: border-box;
        }
        .responsive-video {
            width: 100%;
            height: auto;
            border-radius: 8px; /* Optional: adds rounded corners */
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Optional: adds subtle shadow */
        }
        .caption {
            text-align: center;
            font-family: Arial, sans-serif;
            color: #555;
            margin-top: 15px;
        }
        .caption a {
            color: #0066cc;
            text-decoration: none;
        }
        .caption a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="video-container">
    <video class="responsive-video" controls>
        <source src="${urlField}" type="video/mp4">
        Your browser does not support HTML video.
    </video>

    <p class="caption">
        Video courtesy of
        <a href="https://www.bigbuckbunny.org/" target="_blank">Big Buck Bunny</a>.
    </p>
</div>

</body>
</html>