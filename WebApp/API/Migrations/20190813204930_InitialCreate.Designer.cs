﻿// <auto-generated />
using API.Data;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace API.Migrations
{
    [DbContext(typeof(Context))]
    [Migration("20190813204930_InitialCreate")]
    partial class InitialCreate
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "2.2.6-servicing-10079");

            modelBuilder.Entity("API.Models.Rango", b =>
                {
                    b.Property<int>("RangoId")
                        .ValueGeneratedOnAdd();

                    b.Property<int>("NumerosEncontrados");

                    b.Property<int>("RangoFinal");

                    b.Property<int>("RangoInicial");

                    b.HasKey("RangoId");

                    b.ToTable("Rangos");
                });
#pragma warning restore 612, 618
        }
    }
}
