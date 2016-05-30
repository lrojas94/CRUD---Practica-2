<#include "./header.ftl">
<#if student??>
<div class="container">
    <div class="row">
        <div class="col-xs-3 col-xs-offset-9">
            <div class="btn-group btn-block">
                <button type="button" class="btn btn-success btn-block dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Menu <i class="fa fa-caret-down" ></i>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="/"><i class="fa fa-home"></i> Inicio</a></li>
                    <li><a href="/edit/${student.getMatricula()?string.computer}"><i class="fa fa-pencil"></i> Editar</a></li>
                    <li><a href="/delete/${student.getMatricula()?string.computer}"><i class="fa fa-exclamation-triangle"></i> Eliminar</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6">
            <div class="form-group">
                <label for="matricula">Nombre</label>
                <p>${student.getNombre()}</p>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label for="matricula">Apellidos</label>
                <p>${student.getApellido()}</p>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label for="matricula">Matricula</label>
                <p>${student.getMatricula()?string.computer}</p>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label for="matricula">Telefono</label>
                <p>${student.getTelefono()}</p>
            </div>
        </div>
    </div>

</div>
<#else>
<div class="container-fluid">
    <div class="jumbotron">
        <h1>Estudiante no encontrado</h1>
        <a href="/" class="btn btn-danger"><i class="fa fa-home"></i> Volver al inicio</a>
    </div>
</div>

</#if>
<#include "./footer.ftl">