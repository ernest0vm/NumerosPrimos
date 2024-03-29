# Numeros Primos

## NetCore API (Backend)

- Dependencia de paquetes nuget en API.csproj
```
 <ItemGroup>
    <PackageReference Include="Microsoft.AspNetCore.App" />
    <PackageReference Include="Microsoft.AspNetCore.Razor.Design" Version="2.2.0" PrivateAssets="All" />
    <PackageReference Include="Microsoft.EntityFrameworkCore.Sqlite" Version="2.2.6" />
    <PackageReference Include="Microsoft.VisualStudio.Web.CodeGeneration.Design" Version="2.2.3" />
  </ItemGroup>
```
Para iniciar el proyecto de la API, desde una consola se ejecuta el comando:
```
    dotnet run --urls http://*:58366
```
- Se puede consultar un numero primo en especifico en la url:
```
    http://<ip-server>:58366/api/numbers/{numero a consultar}
```
- Se puede consultar el registro de rangos de la base de datos en la url:
```
    http://<ip-server>:58366/api/ranges
```
- Se puede consultar un rango de numeros primos con los parametros inicio y final:
```
    http://<ip-server>:58366/api/numbers?inicio={numero inicial}&final={numero final}
```

## Screenshots

![screenshot1](https://github.com/ernest0vm/NumerosPrimos/blob/master/Screenshots/api_3.jpg)

## Android Mobile App

Se debe cambiar la ip del servidor en la URL de conexion:
```java
    new HttpAsyncTask(jsonResponse).execute("http://<ip-server>:58366/api/numbers?inicio=" + StartValue + "&final=" + FinalValue);
```

## Screenshots

<img src=https://github.com/ernest0vm/NumerosPrimos/blob/master/Screenshots/Screenshot_1565807064.png width="360" height="720">
<img src=https://github.com/ernest0vm/NumerosPrimos/blob/master/Screenshots/Screenshot_1565807920.png width="360" height="720">
<img src=https://github.com/ernest0vm/NumerosPrimos/blob/master/Screenshots/Screenshot_1565814475.png width="360" height="720">