using Microsoft.EntityFrameworkCore.Migrations;

namespace API.Migrations
{
    public partial class InitialCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Rangos",
                columns: table => new
                {
                    RangoId = table.Column<int>(nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    RangoInicial = table.Column<int>(nullable: false),
                    RangoFinal = table.Column<int>(nullable: false),
                    NumerosEncontrados = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Rangos", x => x.RangoId);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Rangos");
        }
    }
}
