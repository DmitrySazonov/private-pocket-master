[#ftl]
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>private pocket</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>

        body {
                  padding: 1em;

                  font-size: small;
                  background-image: url('/exclusive_paper.png');
                  overflow-y: scroll;
              }
    </style>


</head>
<body class="container">

<form action="/">
    <div class="form-row align-items-center">
       [#if !(name??)]
        <div class="col-sm-5 my-1">
            <label class="sr-only" for="name">Name</label>
            <input type="text" class="form-control mb-2" id="name" placeholder="Input pocket name" name="name" value="${name!}">
                <script>
                    document.getElementById("name").focus();
                </script>
        </div>
        <div class="col-auto my-1">
                <button type="submit" class="btn btn-primary mb-2">Create</button>
        </div>
       [#else]
         <div class="col-sm-5 my-1">
            <label class="sr-only" for="name">Name</label>
            <input type="text" class="form-control mb-2" id="name" placeholder="Input pocket name" name="name" value="${name!}" readonly>
        </div>
        <div class="col-auto my-1">
           <button id="edit" type="button" class="btn btn-light">Edit</button>
        </div>
       [/#if]
    </div>
</form>

[#if name??]

<div class="row">
    [#if records?size == 0]
        <h6 class="col-md-12">No urls</h6>
    [/#if]
    <table class="col-md-12 table table-hover">
        [#list records as record]
        <tr>
            <td>
                [#if record.url?starts_with("http://") || record.url?starts_with("https://")]
                    <h6><a href="${record.url}" target="_blank">${record.url}</a></h6>
                [#else]
                    <h6>${record.url}</h6>
                [/#if]
                [#if record.title??]
                     <h6>${record.title}</h6>
                [/#if]
                <td>
                    <form action="/${pocket}/${record.id}" method="POST">
                    <button class="btn btn-sm btn-warning" type="submit">Delete</button>
                    </form>
                </td>
            </td>
        </tr>
        [/#list]
    </table>
</div>

<form action="/${pocket}" method="post">
    <div class="form-row align-items-center">
        <div class="col-sm-5 my-1">
            <label class="sr-only" for="name">Name</label>
            <input type="text" class="form-control mb-2" id="url" placeholder="Input url or text" name="url">
            [#if name??]
                <script>
                    document.getElementById("url").focus();
                </script>
            [/#if]
        </div>
        <div class="col-auto my-1">
            <button type="submit" class="btn btn-primary mb-2">Save</button>
        </div>
    </div>
</form>

[/#if]

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

</body>
</html>

