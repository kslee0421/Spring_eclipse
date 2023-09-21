<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>5일차 ECMA - 모바일 Swap</title>
    <style>
        html,body{margin: 0; padding: 0;}
        .banner { width: 200px; height: 200px; 
        position: relative; overflow: hidden; border:1px solid red;
        margin: 0 auto;}
        .images{ position: absolute; display: flex;
        transition: all 0.5s ease; }
        .images > img { object-fit: cover; width: 200px; }
    </style>
</head>
<body>
<div class="banner">
<span class="images" id="images">
<img src="../img/cat1.jpg">
<img src="../img/cat2.jpg">
<img src="../img/cat3.jpg">
<img src="../img/cat4.jpg">
</span>
</div>
</body>
</html>