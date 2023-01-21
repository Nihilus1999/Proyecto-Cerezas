
CREATE TABLE AJA_PAIS(
  ID serial unique  NOT NULL, 
  nombre Varchar(20)  NOT NULL, 
  continente Varchar(7) NOT NULL,

  CONSTRAINT pk_pais PRIMARY KEY(ID),   
  CONSTRAINT continente_pais CHECK(continente like 'America' or continente like 'Europa' or continente like 'Asia' or continente like 'Africa' or continente like 'Oceania')
);
INSERT INTO AJA_PAIS (nombre, continente) VALUES
  ('España', 'Europa' ),
  ('Italia', 'Europa' ),
  ('USA', 'America' ),
  ('Chile', 'America' ),
  ('Canadá', 'America' ),
  ('Francia', 'Europa' ),
  ('Alemania', 'Europa' ),
  ('Argentina', 'America' ),
  ('Venezuela', 'America' ),
  ('Turquía', 'Asia' ),
  ('China', 'Asia' ),
  ('Japón', 'Asia' ),
  ('México', 'America' ),
  ('Bulgaria', 'Europa' ),
  ('Colombia', 'America' ),
  ('Paises Bajos', 'Europa' );
--

CREATE TABLE AJA_CIUDAD(
  ID_pais integer NOT NULL, 
  ID serial unique NOT NULL, 
  nombre Varchar(60)  NOT NULL,

  CONSTRAINT pk_ciudad PRIMARY KEY(ID,ID_pais),
  CONSTRAINT fk_pais FOREIGN KEY(ID_pais) REFERENCES AJA_PAIS(ID)
);
INSERT INTO AJA_CIUDAD(ID_pais, nombre) VALUES
  (1, 'Caceres'),
  (1, 'Alicante'),
  (2,'Bari'),
  (2,'Trento'),
  (3,'Traverse City'),
  (3, 'Selah'),
  (4, 'Graneros'),
  (4, 'Santiago'),
  (5, 'Oliver'),
  (10, 'İnegöl'),
  (10, 'Estambul'),
  (13, 'Cuauhtémoc'),
  (14, 'Kuklen'),
  (15, 'Bogota'),
  (15, 'Barranquilla'),
  (7, 'Vaterstetten'),
  (6, 'lattes'),
  (6, 'Bucheres'),
  (7, 'Hollern-Twielenfleth'),
  (6, 'montelimar'),
  (3, 'missisipi');
--

CREATE TABLE AJA_REGION(
  ID_pais integer NOT NULL, 
  ID serial unique NOT NULL, 
  nombre Varchar(40)  NOT NULL, 
  descripcion text  NOT NULL,

  CONSTRAINT pk_region PRIMARY KEY(ID,ID_pais),
  CONSTRAINT fk_pais FOREIGN KEY(ID_pais) REFERENCES AJA_PAIS(ID)
);
INSERT INTO AJA_REGION(ID_pais, nombre, descripcion) VALUES
  (1, 'Valdastilla','lugar ubicado en la region de cascadas de calderon' ),
  (1, 'Valle de las Caderechas','es un territorio del Norte de la provincia de Burgos, integrado administrativamente en la comarca de La Bureba. Está situado físicamente en el extremo Noroeste de la cuenca burebana, al Este del páramo de Masa y al Sur del valle de Valdivielso.' ),
  (1, 'Madrid','comunidad autonoma de madrid' ),
  (2, 'Piamonte','Region metropolitana de italia' ),
  (3, 'Washington ','es un estado al noroeste del Pacífico con un terreno diverso que va desde las nevadas montañas Cascade hasta el interior boscoso en el estrecho de Puget.' ),
  (3, 'Oregon','Estado de oregon en estados unidoses un estado costero de EE.UU. en el noroeste del Pacífico' ),
  (4, 'Los condes','region metropolitana de chile' ),
  (5, 'Columbia Británica','la provincia más occidental de Canadá, se define por su costa en el Pacífico y las cadenas montañosas' ),
  (6, 'Provenza Alpes - Costa sur','una región del sureste de Francia que limita con Italia y el mar Mediterráneo' ),
  (7, 'Baviera','estado del sureste alemán que limita con Liechtenstein, Austria y la República Checa. La capital estatal es Múnich' ),
  (8, 'Patagonia','es una región que abarca el extenso extremo austral de Sudamérica, que comparten Argentina y Chile, con la Cordillera de los Andes como su línea divisoria.' );

  --

CREATE TABLE AJA_VARIEDAD_DE_CEREZAS(
  ID serial unique NOT NULL, 
  nombre Varchar(20)  NOT NULL, 
  especie Varchar(20) NOT NULL, 
  precocidad Varchar(20) NOT NULL, 
  descripcion text  NOT NULL, 
  ID_pais integer NOT NULL ,

  CONSTRAINT  pk_variedad_cereza PRIMARY KEY(ID),
  CONSTRAINT fk_pais FOREIGN KEY(ID_pais) REFERENCES AJA_PAIS(ID),
  CONSTRAINT especie_variedad_cereza Check(especie like 'PC' or especie like 'PA' or especie like 'CD' or especie like 'Otro'),
  CONSTRAINT precocidad_variedad_cereza Check(precocidad like 'ETe' or precocidad like 'Te' or precocidad like 'ME' or precocidad like 'Ta' or precocidad like 'ETa')
);
INSERT INTO AJA_VARIEDAD_DE_CEREZAS(nombre,especie,precocidad,descripcion,ID_pais)  VALUES  
  ('Van','PA','ME','-Tamaño: El tamaño del fruto es de 27-28 mm, resultado el tamaño de la cereza van de medio a grueso. El tamaño resulta aceptable, si tenemos en cuenta la elevada producción que puede llegar a presentar esta variedad de cerezo.

  -Forma: La cereza dispone de forma reniforme.

  -Color de la piel: Cuando se encuentra madura, presenta un color granate muy atractivo comercialmente.

  -Color de pulpa: La carne de la cereza dispone de un color rojo oscuro.

  -Sabor: La cereza Van, está catalogada con un sabor muy bueno. Tengo varios cerezos de esta variedad y puedo asegurar que es una cereza deliciosa.

  -Firmeza: La cereza es de consistencia firme, lo que nos aporta esa sensación crujiente tan agradable al comerlas.

  -Resistencia al agrietado: La resistencia al agrietado es Buena, un escalón por debajo de la máxima resistencia existente al agrietado.

  -Zona de agrietado: El cracking se produce generalmente en la zona apical de la cereza.

  -Longitud del pedúnculo: La longitud del pedúnculo es corta. Esta característica ayuda a que la cereza presente visualmente un mayor tamaño del que le corresponde. Sin embargo, el pedúnculo corto puede dificultar ligeramente la recogida del fruto.',5),
  ('Ambrunés','PA','ETa','-Tamaño: La cereza Ambrunesa, tiene un calibre o tamaño medio. Al alcanzar la madurez, Ambrunés tiene un tamaño de 26-27 mm. Aunque no es un tamaño grueso, al ser habitualmente cosechada sin rabo, visualmente aparenta un buen tamaño.

  -Forma: La variedad de cereza Ambrunés presenta forma redonda-aplanada.

  -Color de la piel: La cereza Ambrunesa, presenta un atractivo color granate durante la época de cosecha. El color, resulta comercialmente atractivo.

  -Color de pulpa: La cereza Ambrunés, tiene la pulpa de color Rojo.

  -Sabor: La variedad de cereza Ambrunés, es una variedad apreciada por su sabor muy bueno.

  -Firmeza: La cereza Ambrunesa, es firme. Por su buena firmeza, Ambrunés es una variedad de buen aguante postcosecha. Es una cereza buena para el transporte. Por su firmeza, podría considerarse una cereza crujiente, muy valorada por los consumidores españoles.

  -Resistencia al agrietado: La variedad de cerezo Ambrunés, tiene muy buena resistencia al cracking, agrietado o partidura. Esta es una de las principales claves para su importante extensión por la lluviosa zona del Valle del Jerte.',1),
  ('Lapins','PA','Ta','-Tamaño: la cereza Lapins, presenta un tamaño medio-grueso (27-28 mm).

  -Forma: Dependiendo del autor, la variedad de cereza Lapins tiene una forma redonda aplanada o algo alargada. Bajo mi opinión, la forma de Lapins resulta algo alargada.

  -Color de la piel: La madurez se logra con la piel de color rojo oscuro.

  -Color de pulpa: Cuando se alcanza la madurez, la variedad Lapins presenta un color de pulpa rojo.

  -Sabor: La cereza Lapins es considerada como una cereza de buen sabor.

  -Firmeza: Lapins es una cereza firme.

  -Resistencia al agrietado: Presenta una resistencia al agrietado muy buena. Se podría llegar a decir que Lapins es tolerante al cracking, lo que la hace especialmente interesante para climas lluviosos, asegurando la mayor parte de la producción a pesar de lluvias importantes.

  -Longitud del pedúnculo: La longitud del pedúnculo es media en la cereza Lapins.',5),
  ('Napoleon','PA','Ta','-Tamaño: El tamaño de los frutos cosechados es medio, la cereza Napoleón dispone en su madurez de un calibre de 26 mm.

  -Forma: La cereza Napoleon tiene forma alargada.

  -Color de la piel: La variedad de cereza Napoleón, tiene color rojo sobre crema a la madurez.

  -Color de pulpa: Cuando la cereza alcanza la madurez, la pulpa tiene un color blanco crema.

  -Sabor: Napoleon es tenida por una cereza de sabor escaso. El contenido de azúcar es bajo en esta cereza.

  -Firmeza: Dependiendo de la fuente consultada, la cereza Napoleon presenta firmeza débil o media.

  -Resistencia al agrietado: La variedad de cerezo Napoleón buena resistencia al agrietado.  Sin embargo, esto puede relacionarse con sus condiciones de firmeza media-débil y escaso nivel de azúcares.',7),
  ('Burlat','PA','Te','-Tamaño: Pueden conseguirse cerezas de 27-28 mm de media, Presentando Burlat un tamaño de cereza de medio a grueso.

  -Forma: La variedad de cereza Burlat, presenta forma redonda-aplanada.

  -Color de la piel: La cereza Burlat alcanza la madurez con un color de piel rojo oscuro.

  -Color de pulpa: la pulpa de la variedad de cereza Burlat tiene un color de rojo.

  -Sabor: Bigarreau Burlat, se encuentra catalogada con un sabor bueno o medio según la referencia. En mi opinión, su sabor es bueno para tratarse de una variedad temprana.

  -Firmeza: La cereza Burlat, es una variedad de cereza de dureza media o fuerte según la fuente de consulta. Personalmente, diría que esta cereza tiene una firmeza media.',6),
  ('Bing','PA','ME','-Tamaño: Finalizado su crecimiento, el tamaño de las cerezas cosechadas es medio y homogéneo. La cereza Bing tiene un tamaño medio de 26 mm.

  -Forma: La variedad de cereza Bing tiene forma redonda-aplanada.
  Color de la piel: La variedad de cereza bing, tiene color negruzco a la madurez.

  -Color de pulpa: Cuando la cereza alcanza la madurez, la pulpa tiene un color rojo oscuro.

  -Sabor: La cereza Bing tiene muy buen sabor. El mercado Americano valora mucho esta cereza, al estar muy acostumbrados a su consumo.
  Firmeza: Dependiendo de la fuente consultada, La cereza Bing presenta firmeza media.

  -Resistencia al agrietado: La variedad de cerezo Bing tiene poca resistencia al agrietado. Esta característica ha dificultado un mayor auge de esta variedad en España.',3),
  ('Marvin','PA','ETe','Tamaño: La cereza Marvin, tiene un tamaño medio-grueso. Durante la recogida, la variedad Niran alcanza un tamaño de 27-28 mm.

  Forma: La variedad de cereza Marvin presenta forma redonda-aplanada. Según otras fuentes la forma es reniforme.

  Color de la piel: La cereza 4-70, se recolecta cuando tiene color Rojo Oscuro.

  Color de pulpa: Marvin, tiene la pulpa de color Rojo.

  Sabor: La cereza Marvin, es una variedad de sabor bueno.

  Firmeza: La cereza Marvin, tiene firmeza media. Otras fuentes indican buena firmeza.
  Por su cosecha temprana, tuvo una gran expansión, sin embargo, sus altas necesidades de horas frío, hicieron que fracasasen grandes plantaciones en el sur de España. Hoy en día es una variedad en decadencia.

  Resistencia al agrietado: La variedad de cerezo Marvin, tiene poca resistencia a la partidura o cracking. Siendo una cereza poco interesante para zonas donde existe riesgo de lluvia.',3),
  ('Pico Colorado','PA','ETa','Tamaño: El tamaño del fruto es muy pequeño, con un diámetro de apenas 22-23 mm.

  Forma: La cereza Pico Colorado es de forma cordiforme.

  Color de la piel: Cuando alcanza la madurez, la cereza es de color rojo.

  Color de pulpa: La carne de la cereza es de color amarillo crema.

  Sabor: La variedad de cereza Pico Colorado, es de sabor bueno.

  Firmeza: Pico Colorado es una cereza de consistencia firme, con el toque crujiente característico de las cerezas picotas y que aporta una agradable sensación crujiente al consumirlas.

  Resistencia al agrietado: La cereza Pico Colorado, dispone de Buena resistencia al cracking o agrietado, un punto por debajo de la máxima calificación de resistencia. Al ser una cereza de maduración muy tardía, los daños por agrietado suelen ser escasos.',1),
  ('Pico Negro','PA','ETa','-Tamaño: El tamaño del fruto es de tamaño entre pequeño y medio, con un diámetro de 25-26 mm.

  -Forma: La variedad cereza Pico Negro tiene forma cordiforme.

  -Color de la piel: Al alcanzar la madurez, la cereza es de color 
  negruzco.

  -Color de pulpa: La pulpa de la cereza es de color rojo oscuro.

  -Sabor: La variedad de cereza Pico Negro, es de sabor muy bueno.

  -Firmeza: Pico Colorado es una cereza de consistencia firme, con la dureza y sensación crujiente característica de las cerezas del tipo picota. Por su firmeza es apta para el transporte y tiene buen aguante post-cosecha.

  -Resistencia al agrietado: Pico Negro, tiene muy buena resistencia a la partidura, agrietado o cracking.',1),
  ('Early Red','PA','Te','-Tamaño: La cereza Early Red es de tamaño grueso, alcanza de 28-30 mm de diámetro.

  -Forma: La variedad de cereza Early Red, tiene forma reniforme.

  -Color de la piel: Early Red alcanza la madurez cuando presenta color Granate.

  -Color de pulpa: La variedad de cereza Early Red, dispone de pulpa de color rojo oscuro.

  -Sabor: El sabor de la cereza Early Red, es muy bueno.

  -Firmeza: La cereza Early Red es firme.  Es una cereza de elevada firmeza, fuentes alemanas la otorgan un Durofel de 75-80.

  -Resistencia al agrietado: Early Red dispone de poca resistencia al cracking, agrietado o partidura. En Italia recomiendan cubierta plástica para las plantaciones de esta variedad de cereza.',3),
  ('Celeste','PA','Te','-Tamaño: El tamaño del fruto es muy grueso, poseyendo la cereza Celeste un tamaño de 29-30 mm.

  -Forma: Celeste, dispone de forma reniforme.

  -Color de la piel: La variedad de cereza Celeste, presenta color granate a madurez.

  -Color de pulpa: En estado de madurez, la cereza Celeste presenta color de pulpa rojo.

  -Sabor: Se considera muy bueno el sabor de la cereza Celeste.

  -Firmeza: La firmeza de Celeste, se encuentra catalogada como media.

  -Resistencia al agrietado: La variedad de cereza Celeste, es muy poco resistente al cracking. Siendo probablemente este, su mayor punto débil como variedad. Resulta arriesgada su plantación en climas lluviosos.

  -Zona de agrietado: Los daños por agrietado o cracking, pueden producirse en la zona general o apical de la cereza.',5),('Kordia','PA','Ta','-Tamaño: La cereza Kordia, tienen tamaño grueso de 28 mm.

  -Forma: Attika tiene forma cordiforme o acorazonada, muy atractiva comercialmente.

  -Color de la piel: La variedad de cereza Kordia, tiene color rojo oscuro brillante a la madurez.

  -Color de pulpa: la pulpa tiene color Rojo.

  -Sabor: Kordia es una cereza dulce de sabor bueno.

  -Firmeza: La cereza Kordia es firme. Es una de las cerezas más apreciadas por los exportadores, gracias a su alta capacidad de aguante post-cosecha.

  -Resistencia al agrietado: La variedad de cereza Kordia, dispone de buena resistencia al agrietado.',2),('Regina','PA','ETa','-Tamaño: Las cerezas de Regina tienen tamaño grueso de 28-29mm.

  -Forma: La cereza Regina tiene forma redondeada.

  -Color de la piel: La variedad de cereza Regina, dispone de color granate a madurez.

  -Color de pulpa: la pulpa tiene color Rojo.

  -Sabor: El sabor de la cereza Regina es bueno. Algunas fuentes la clasifican como de sabor normal, inferior a Skeena (muy buen sabor).

  -Firmeza: La cereza Regina es firme.

  -Resistencia al agrietado: La variedad de cereza Regina tiene muy buena resistencia al cracking, agrietado o partidura. Por ello, está clasificada dentro del grupo de las variedades que disponen de mejor aguante al cracking.',4),('Marasca','PA','Ta','-Tamaño: La cereza Marasca, tienen tamaño grueso de 27 mm.

  -Forma: tiene forma cordiforme o acorazonada, muy atractiva comercialmente.

  -Color de la piel: La variedad de cereza Marasca, tiene color rojo claro cuando llega a la madurez.

  -Color de pulpa: la pulpa tiene color Rojo.

  -Sabor: la cereza Marasca es una cereza acida de sabor medio amargo.

  -Firmeza: La cereza Marasca es firme, gracias a su alta capacidad de aguante post-cosecha.

  -Resistencia al agrietado: La variedad de cereza Marasca, dispone de buena resistencia al agrietado.',2),('Montmorency','PA','Te','-Tamaño: Pueden conseguirse cerezas de 29-30 mm de media, un tamaño de cereza de medio a grueso.

  -Forma: La variedad de cereza Montmorency, presenta forma redonda.

  -Color de la piel: La cereza Montmorency alcanza la madurez con un color de piel rojo-amarillo claro.

  -Color de pulpa: la pulpa de la variedad de cereza Montmorency tiene un color de rojo.

  -Sabor: la cereza Montmorency es una cereza acida de sabor medio amargo.

  -Firmeza: La cereza Montmorency, es una variedad de cereza de dureza media o fuerte según la fuente de consulta.',6),('Amarena','PA','Ta','-Tamaño: La cereza Amarena, tienen tamaño grueso de 28 mm.

  -Forma: tiene forma cordiforme o acorazonada.

  -Color de la piel: La variedad de cereza Amarena, tiene color rojo oscuro cuando llega a la madurez.

  -Color de pulpa: la pulpa tiene color negro.

  -Sabor: la cereza amarena es una cereza acida de sabor medio amargo.

  -Firmeza: La cereza Marasca es firme, gracias a su alta capacidad de aguante post-cosecha.

  -Resistencia al agrietado: La variedad de cereza Marasca, dispone de buena resistencia al agrietado.',2);
--

CREATE TABLE AJA_HISTORICO_PRECIO_VARIEDAD(
  ID_pais integer NOT NULL, 
  ID_variedad integer NOT NULL, 
  ID serial unique NOT NULL, 
  fecha_inicio Date  NOT NULL, 
  precio Numeric(20)  NOT NULL, 
  calibre Numeric(20)  NOT NULL, 
  fecha_final Date,

  CONSTRAINT  pk_historico_precio_variedad PRIMARY KEY(ID,ID_variedad,ID_pais),
  CONSTRAINT fk_pais FOREIGN KEY(ID_pais) REFERENCES AJA_PAIS(ID) ON DELETE CASCADE,
  CONSTRAINT fk_variedad FOREIGN KEY(ID_variedad) REFERENCES AJA_VARIEDAD_DE_CEREZAS(ID) ON DELETE CASCADE
);
INSERT INTO AJA_HISTORICO_PRECIO_VARIEDAD(ID_pais,ID_variedad,fecha_inicio,precio,calibre, fecha_final) VALUES
(1,1, '2020-10-14',8,27,NULL),
(1,2, '2020-06-15',7,26,NULL),
(1,3, '2018-03-23',9,28,NULL),
(1,5, '2018-04-26',10,28,NULL),
(1,7, '2021-11-29',10,27,NULL),
(1,8, '2022-04-04',14,27,NULL),
(1,9, '2019-06-27',9,25,NULL),
(2,12, '2020-03-14',10,26,NULL),
(2,7, '2021-06-15',12,27,NULL),
(3,3, '2016-04-12',8,27,'2018-10-09'),
(3,3, '2020-11-10',9,27,NULL),
(3,6, '2018-09-08',14,26,NULL),
(3,7, '2022-03-24',11,28,'2020-11-09'),
(3,10, '2020-05-22',17,31,NULL),
(4,3, '2014-05-20',7,28,NULL),
(5,1, '2016-12-05',5,28,NULL),
(5,3, '2021-04-28',6,28,'2019-02-20'),
(5,6, '2020-10-17',12,26,NULL),
(5,11, '2019-12-15',16,30,'2021-06-07'),
(6,4, '2019-06-20',7,26,NULL),
(6,5, '2013-05-08',13,27,NULL),
(7,5, '2021-07-11',15,27,NULL),
(7,4, '2020-05-15',9,26,NULL),
(2,14, '2019-07-19',2,28,NULL),
(6,16, '2018-04-24',3,28,'2021-07-10'),
(2,15, '2020-06-19',4,30,NULL);
--

CREATE TABLE AJA_PROVEEDOR(
  ID serial unique NOT NULL, 
  nombre Varchar(50)  NOT NULL, 
  ramo Varchar(20) NOT NULL, 
  descripcion_ramo text  NOT NULL, 
  ID_pais integer  NOT NULL,

  CONSTRAINT  pk_proveedor PRIMARY KEY(ID),
  CONSTRAINT fk_pais FOREIGN KEY(ID_pais) REFERENCES AJA_PAIS(ID),
  CONSTRAINT ramo_proveedor check(ramo like 'transporte' or ramo like 'fertilizante' or ramo like 'tecnologia' or ramo like 'recolectores' or ramo like 'drones' or ramo like 'otros')
);
INSERT INTO AJA_PROVEEDOR(	ID_pais	,	nombre	,	ramo	,	descripcion_ramo	) VALUES
  (1,'Fertiberia','fertilizante','Todo tipo de fertilizante'),
  (1,'FerroIce','tecnologia','Cámaras de conservación'),
  (9,'AgriNova','otros','Insumos Agrícolas'),
  (10,'Ergene Fidancilik','recolectores','distribuida de máquina para el cultivo'),
  (10,'STOPARTS LTD','tecnologia','Proveedor de repuestos para maquinaria pesada y equipos especiales'),
  (5,'AK Consulting International Inc.','transporte','Exportaciones'),
  (16,'Somac','tecnologia','Maquinaria variada'),
  (11,'Golden Machinery','tecnologia','Maquinaria procesamiento de cerezas'),
  (12,'Monotaro','fertilizante','Todo tipo de fertilizante');
--

CREATE TABLE AJA_ASOCIACION_REGIONAL(
  ID serial unique NOT NULL, 
  nombre Varchar(60)  NOT NULL, 
  descripcion text  NOT NULL, 
  ID_region integer NOT NULL,
  ID_pais integer NOT NULL,

  CONSTRAINT  pk_asociacion_regional PRIMARY KEY(ID),
  CONSTRAINT fk_region FOREIGN KEY(ID_region,ID_pais) REFERENCES AJA_REGION(ID,ID_pais)
);
INSERT INTO AJA_ASOCIACION_REGIONAL(	nombre	,	descripcion	,	ID_region, ID_pais	)  VALUES 
  ('Mesa Sectorial de la Cereza','La mesa sectorial de la cereza nace en 2017 tras contactos reiterados entre empresas e instituciones de las distintas zonas productoras de cereza del territorio español. La necesidad de enfrentar problemas específicos, no comunes a otras frutas de hueso, entre las que tradicionalmente se ha encontrato la cereza fue el objetivo principal de su germen.',1,1),
  ('Asociación Las Caderechas','La Asociación de Productores y Comerciantes Las Caderechas fue fundada en el año 2000.

  Esta Asociación está formada por pequeños empresarios y trabajadores autónomos cuya actividad sea la producción y comercialización de productos agrarios.',2,1),
  ('Asociacion Fepex','FEPEX, la Federación Española de Asociaciones de Productores Exportadores de Frutas, Hortalizas, Flores y Plantas vivas, es una organización sectorial, de carácter privado, cuyas principales funciones son contribuir y fomentar la competitividad de los sectores representados y prestar servicios a las Asociaciones integradas, prestando servicios en los ámbitos de la producción y de los mercados, así como ejerciendo funciones de representación e interlocución ante las distintas administraciones, instituciones y órganos de decisión, tanto nacionales como internacionales para que el marco regulatorio de la actividad del sector se adecue a sus características y prioridades.',3,1),
  ('Asociación de Frutas','La Asociación de Exportadores de Frutas de Chile A.G. (ASOEX) es una entidad gremial, privada y sin fines de lucro, la cual cuenta con más de 350 empresas productoras-exportadoras de fruta fresca asociadas en sus diferentes programas.',7,4),
  ('Capci','La Asociación Argentina de Productores Integrados de Cerezas (CAPCI) es una organización conformada por los principales productores y exportadores de cerezas del país, que representan más del 90% de las exportaciones de Argentina',11,8),
  ('Northwest Cherry Growers','La Comisión de Frutas del Estado de Washington, una agencia estatal, fue fundada en 1947 y está financiada por evaluaciones de productores con el propósito de promoción, desarrollo de mercado, investigación y educación para los frutos rojos cultivados en el estado de Washington.',5,3),
  ('Oregon Cherry Growers','La comida representa un mundo de imaginación, arte, determinación y pasión, y en la Asociación de Alimentos Especializados, representamos ese mundo. También lo fortalecemos, lo movemos y empujamos sus límites todos los días.

  Es por eso que fuimos creados, para servir como un agente para la industria y toda su gente, desde los que cultivan cosas hasta los que las hacen, las compran, las distribuyen y más allá. Estamos dedicados a unir a estas diversas comunidades.',6,3),
  ('BC Cherry Association','Anteriormente llamada Okanagan Kootenay Cherry Growers Association (OKCGA), la organización fue formada en 1998 por un pequeño grupo de productores de cerezas que buscaban reunir recursos financieros para aumentar la investigación y el desarrollo de la producción de cerezas en la región. A través de una contribución voluntaria de un centavo por libra de cada productor, se han logrado avances significativos, no solo en investigación, sino en otras áreas, como el registro de pesticidas de uso menor, problemas de huertos y empleo, y foros para productores. Desde su inicio, las contribuciones anuales de los productores, asociadas con programas provinciales y federales, han permitido cinco o seis proyectos de investigación prioritarios al año, trabajando con el Centro de Investigación Agrícola del Pacífico (PARC) y otros investigadores y consultores.',8,5),
  ('Gscheitgut','»Gscheitgut« representa la diversidad culinaria, el apetito por losproductos regionalesy un enfoque sostenible del paisaje cultural de Franconia.',10,7),
  ('Frutticoltori Associati della Collina Torinese','La FACOLT – Frutticoltori Associati Collina Torinese – es una asociación sin fines de lucro de productores de frutas de la Collina Torinese, fundada en 1983 por iniciativa de los centros locales de asistencia agrícola, asociaciones comerciales, la Universidad de Turín y los municipios de Trofarello y Pecetto Torinese. Este último también ha otorgado la sede y los terrenos del legado Gonella para convertirlos en campos de investigación experimentales y frutícolas que ahora se incluyen en el proyecto de parque urbano más grande.',4,2),
  ('AOP Cerises de France','La FACOLT – Frutticoltori Associati Collina Torinese – es una asociación sin fines de lucro de productores de frutas de la Collina Torinese, fundada en 1983 por iniciativa de los centros locales de asistencia agrícola, asociaciones comerciales, la Universidad de Turín y los municipios de Trofarello y Pecetto Torinese. Este último también ha otorgado la sede y los terrenos del legado Gonella para convertirlos en campos de investigación experimentales y frutícolas que ahora se incluyen en el proyecto de parque urbano más grande.',9,6);
--

CREATE TABLE AJA_CONVENIO(
  ID_asociacion integer NOT NULL, 
  ID_proveedor integer NOT NULL, 
  fecha_firma Date NOT NULL, 
  beneficios Varchar(200)  NOT NULL, 
  vigencia Boolean  NOT NULL,

  CONSTRAINT fk_asociacion_regional FOREIGN KEY(ID_asociacion) REFERENCES AJA_REGION(ID) ON DELETE CASCADE,
  CONSTRAINT fk_proveedor FOREIGN KEY(ID_proveedor) REFERENCES AJA_REGION(ID) ON DELETE CASCADE,
  CONSTRAINT  pk_convenio PRIMARY KEY(ID_asociacion,ID_proveedor,fecha_firma)
);
INSERT INTO AJA_CONVENIO (ID_asociacion,ID_proveedor,fecha_firma,beneficios,vigencia) VALUES
  (1,1, '2020-04-18','Ofrecer fertilizante para mejorar la productividad y cantidad de las cerezas',FALSE),
  (1,2, '2020-04-19','Conservar los productos mas tiempo y de manera mas eficiente',FALSE),
  (1,9, '2020-04-20','Vender los productos de España en el mercado asiatico',FALSE),
  (2,1, '2020-06-01','ofrecer fertilizante para mejorar la productividad y cantidad de las cerezas',TRUE),
  (2,7, '2020-06-01','Ofrecer productos de calidad que ayude a mejorar la productividad de las cerezas',TRUE),
  (3,2, '2017-09-10','Conservar los productos mas tiempo y de manera mas eficiente',TRUE),
  (4,6, '2020-06-03','Adquirir productos agricolas de manera segura y confiable',FALSE),
  (4,8, '2020-06-03','Mejorar el producción de procesamiento de frutas y verduras',FALSE),
  (5,8, '2020-10-01','Mejorar el producción de procesamiento de frutas y verduras',FALSE),
  (6,7, '2017-05-09','Ofrecer productos de calidad que ayude a mejorar la productividad de las cerezas',TRUE),
  (7,9, '2018-04-17','Vender los productos de España en el mercado asiatico',TRUE),
  (7,1, '2018-04-17','ofrecer fertilizante para mejorar la productividad y cantidad de las cerezas',TRUE),
  (8,6, '2021-05-24','Adquirir productos agricolas de manera segura y confiable',TRUE),
  (8,7, '2021-05-24','Ofrecer productos de calidad que ayude a mejorar la productividad de las cerezas',TRUE),
  (9,5, '2021-08-04','Ofrece repuestos para todo tipo de maquinaria y equipos pesados ​​de fabricantes y distribuidores',FALSE),
  (9,7, '2021-08-04','Ofrecer productos de calidad que ayude a mejorar la productividad de las cerezas',FALSE),
  (10,4, '2020-06-12','Ofrecer maquinas distribuidas que ayuden  a mejorar la calidad del cultivo',TRUE),
  (10,2, '2020-06-12','Conservar los productos mas tiempo y de manera mas eficiente',TRUE),
  (11,7, '2018-04-27','Ofrecer productos de calidad que ayude a mejorar la productividad de las cerezas',TRUE),
  (11,6, '2018-04-27','Adquirir productos agricolas de manera segura y confiable',TRUE);
--

CREATE TABLE AJA_PRODUCTOR(
  ID serial unique NOT NULL, 
  nombre Varchar(100)  NOT NULL, 
  direccion Varchar(100)  NOT NULL, 
  envase_estandar Varchar(20)  NOT NULL, 
  ID_ciudad integer  NOT NULL,  
  ID_pais_ciudad integer not null,
  ID_region integer,  
  ID_pais_region integer,
  ID_padre integer, 
  pagina_web Varchar(200),

  CONSTRAINT  pk_productor PRIMARY KEY(ID),
  CONSTRAINT fk_region FOREIGN KEY(ID_region,ID_pais_region) REFERENCES AJA_REGION(ID,ID_pais),
  CONSTRAINT fk_ciudad FOREIGN KEY(ID_ciudad,ID_pais_ciudad) REFERENCES AJA_CIUDAD(ID,ID_pais)
);
ALTER TABLE AJA_PRODUCTOR ADD CONSTRAINT fk_padre FOREIGN KEY(ID_padre) REFERENCES AJA_PRODUCTOR(ID);
-- Esto se hizo de este modo solo para el orden 
INSERT INTO AJA_PRODUCTOR (nombre,direccion,envase_estandar,ID_ciudad,ID_pais_ciudad,ID_region,ID_pais_region, id_padre,pagina_web) VALUES
  ('Molina y Azorín Cherries','Partida el cascante, 190. Villena (Alicante).','Caja de caton',2,1,9,6,null, 'https://www.molinayazorin.es/'),
  ('Cerezas del Jerte','Valdastillas, Cáceres, España','Malla',1,1,2,1,null, 'https://cerezasdeljerte.com/'),
  ('Agrícola Prime','Via Art. M. Campanella, 17, 70010 Sammichele di Bari BA, Italia','Bolsa plastica ',3,2,4,2,null, 'https://agricolaprime.com/'),
  ('La trentina','Via Trento, 15 - 38052 Caldonazzo TN, Italia','Cajon de madera',4,2,null,null,null, 'https://www.latrentina.it/prodotti/ciliegia'),
  ('Cherry Republic','154 E. Front St Traverse City, MI 49684','Caja poliestireno ',5,3,null,null,null, 'https://cherryrepublic.com/'),
  ('Rainier Fruit','352 Harrison Rd, Selah, WA 98942, Estados Unidos','Caja de carton',6,3,null,null,13, 'https://rainierfruit.com/cherries'),
  ('Trumao Cherries','H-10 04285, Graneros, O Higgins, Chile','Malla',7,4,7,4,null, 'https://www.trumaocherries.cl/'),
  ('Rocofrut','Longitudinal sur Km. 187, Casilla 16-D, Chile','Bolsa plastica ',8,4,7,4,null, 'https://rocofrut.cl/'),
  ('Gold Star Fruit Company','243 Miller RD., Oliver, BC V0H1T1','Cajon de madera',9,5,8,5,null, 'https://goldstarfruitcompany.ca/our-products/'),
  ('International Plant Selection','Route de Marseille - BP 125 26200 Montélimar - FRANCE','Caja poliestireno ',20,6,null,null,13, 'https://www.ips-plant.com/en/'),
  ('Obsthof Eckhoff','Am Deich 52, 21723 Hollern-Twielenfleth, Alemania','Caja de caton',19,7,null,null,14, 'http://www.obsthofeckhoff.de/kirschen.html'),
  ('Southern Crops','77 Cowardtown Rd, Morton, MS 39117, Estados Unidos','Malla',21,3,null,null,14, 'http://www.southerncrops.com.ar/produccion.html'),
  ('Consommons cooperatif en occitanie','Rdpt de la Vierge, 34970 Lattes, Francia','Caja de caton',17,6,null,null,null, 'https://consommonscooperatif.com/les-produits-des-coops/'),
  ('VIVESCIA','Rte de Verrières, 10800 Buchères, Francia','Malla',18,6,null,null,null, 'https://www.vivescia.com/grand-angle');
UPDATE AJA_PRODUCTOR 
  SET ID_padre= 13
  WHERE ID=6 OR ID= 10;
UPDATE AJA_PRODUCTOR 
  SET ID_padre= 14
  WHERE ID=11 OR ID= 12;  
  
--

CREATE TABLE AJA_TRABAJAN(
  ID_productor integer NOT NULL , 
  ID_proveedor integer  NOT NULL ,

  CONSTRAINT pk_trabajan PRIMARY KEY(ID_productor),
  CONSTRAINT fk_proveedor FOREIGN KEY(ID_proveedor) REFERENCES AJA_PROVEEDOR(ID),
  CONSTRAINT fk_productor FOREIGN KEY(ID_productor) REFERENCES AJA_productor(ID)
  
);
INSERT INTO AJA_TRABAJAN  VALUES
  (4,1),
  (8,2),
  (10,3),
  (6,4),
  (7,5),
  (2,6),
  (5,7),
  (11,8),
  (3,9),
  (9,5),
  (12,6),
  (1,7);
--

CREATE TABLE AJA_CONVENIO_DE_TRABAJO(
  ID_proveedor integer NOT NULL, 
  ID_productor integer NOT NULL, 
  fecha_firma Date NOT NULL, 
  beneficios Varchar(200)  NOT NULL, 
  vigencia Boolean  NOT NULL,
  
  
  CONSTRAINT fk_proveedor FOREIGN KEY(ID_proveedor) REFERENCES AJA_PROVEEDOR(ID) ON DELETE CASCADE,
  CONSTRAINT fk_productor FOREIGN KEY(ID_productor) REFERENCES AJA_productor(ID) ON DELETE CASCADE,
  CONSTRAINT pk_convenio_de_trabajo PRIMARY KEY(ID_proveedor,ID_productor,fecha_firma)
);
INSERT INTO AJA_CONVENIO_DE_TRABAJO  VALUES
  (1,2, '2020-08-16','Comerciar cerezas exclusivas como Van, Ambrunes o Celeste',FALSE),
  (2,1, '2021-02-10','Comercializar cerezas y tener una exclusividad con cerezas unicas en esa region',TRUE),
  (2,4, '2019-03-29','Fomentar el comercio entre Italia y españa mendiante una mejora de transporte de cerezas',TRUE),
  (1,3, '2018-07-10','El proveedor ofrece un ciudado de los cultivos del productor',FALSE),
  (2,10, '2022-05-04','Fomentar el comercio entre francia y españa garantizando un servicio de transporte eficiente',FALSE),
  (3,12, '2019-09-17','Vender productos de cultivo de cerezas a un precio mas asequible ',FALSE),
  (6,5, '2017-05-05','Ofrecer cerezas acidas a un precio inferior al del mercado global',TRUE),
  (6,9, '2019-11-07','Comercializar cerezas exclusivas de Canada hacia Estados Unidos',FALSE),
  (7,11, '2017-07-21','Ofrecer productos y maquinaria exclusivos que ayuden a mejorar la productividad de las cerezas en Alemania',TRUE);
--

CREATE TABLE AJA_REPRESENTACION(
  ID_asociacion integer NOT NULL, 
  ID_productor integer NOT NULL,

  CONSTRAINT fk_asociacion FOREIGN KEY(ID_asociacion) REFERENCES AJA_ASOCIACION_REGIONAL(ID) ON DELETE CASCADE,
  CONSTRAINT fk_productor FOREIGN KEY(ID_productor) REFERENCES AJA_productor(ID) ON DELETE CASCADE,
  CONSTRAINT pk_representacion PRIMARY KEY(ID_productor,ID_asociacion)
);
INSERT INTO AJA_REPRESENTACION  VALUES
  (1,7),
  (4,8),
  (7,9),
  (6,1),
  (8,2),
  (2,3),
  (3,4),
  (9,5),
  (5,6);
--

CREATE TABLE AJA_CULTIVO(
  ID_productor integer NOT NULL , 
  ID_variedad integer NOT NULL , 
  ID serial unique NOT NULL, 
  produccion_anual_estimada Numeric(20)  NOT NULL, 
  periodo_inicio Date  NOT NULL, 
  Periodo_fin Date  NOT NULL, 
  maximo_exportar Numeric(20)  NOT NULL, 
  calibre Numeric(20),

  CONSTRAINT fk_variedad FOREIGN KEY(ID_variedad) REFERENCES AJA_VARIEDAD_DE_CEREZAS(ID),
  CONSTRAINT fk_productor FOREIGN KEY(ID_productor) REFERENCES AJA_productor(ID),
  CONSTRAINT pk_cultivo PRIMARY KEY(ID,ID_productor,ID_variedad)
);
INSERT INTO AJA_CULTIVO (ID_productor,ID_variedad,produccion_anual_estimada,periodo_inicio,Periodo_fin,maximo_exportar,calibre) VALUES
(1,2,3000000, to_date('4/12','mm/dd'), to_date('10/20','mm/dd'),450000,27),
(1,3,5000000, to_date('4/21','mm/dd'), to_date('12/18','mm/dd'),750000,28),
(1,5,4000000, to_date('3/19','mm/dd'), to_date('6/7','mm/dd'),600000,28),
(2,7,2000000, to_date('1/1','mm/dd'), to_date('12/2','mm/dd'),300000,27),
(2,8,3500000, to_date('4/17','mm/dd'), to_date('7/21','mm/dd'),525000,22),
(2,9,2500000, to_date('1/10','mm/dd'), to_date('10/16','mm/dd'),375000,25),
(3,7,17000000, to_date('1/19','mm/dd'), to_date('8/2','mm/dd'),1700000,27),
(4,12,13000000, to_date('1/17','mm/dd'), to_date('11/1','mm/dd'),1300000,29),
(5,1,80000000, to_date('2/23','mm/dd'), to_date('11/11','mm/dd'),24000000,28),
(5,3,60000000, to_date('4/2','mm/dd'), to_date('9/9','mm/dd'),18000000,27),
(6,6,90000000, to_date('3/14','mm/dd'), to_date('6/29','mm/dd'),27000000,26),
(6,7,70000000, to_date('4/16','mm/dd'), to_date('6/1','mm/dd'),21000000,28),
(6,10,60000000, to_date('1/9','mm/dd'), to_date('8/13','mm/dd'),18000000,30),
(7,4,180000000, to_date('1/29','mm/dd'), to_date('8/15','mm/dd'),135000000,26),
(8,3,220000000, to_date('3/13','mm/dd'), to_date('10/20','mm/dd'),165000000,29),
(9,1,6500000, to_date('5/22','mm/dd'), to_date('11/15','mm/dd'),1300000,27),
(9,3,7000000, to_date('5/16','mm/dd'), to_date('12/15','mm/dd'),1400000,28),
(9,6,5000000, to_date('4/18','mm/dd'), to_date('12/24','mm/dd'),1000000,26),
(10,4,7000000, to_date('2/14','mm/dd'), to_date('8/28','mm/dd'),1400000,26),
(10,5,9000000, to_date('5/6','mm/dd'), to_date('12/10','mm/dd'),1800000,27),
(11,4,55000000, to_date('2/27','mm/dd'), to_date('10/3','mm/dd'),11000000,26),
(12,14,65000000, to_date('1/4','mm/dd'), to_date('8/6','mm/dd'),45500000,29);
--

CREATE TABLE AJA_PRODUCCION_ANUAL_REAL(
  ID_cultivo integer NOT NULL, 
  ID_productor integer NOT NULL , 
  ID_variedad integer NOT NULL , 
  YYYY integer NOT NULL,
  produccion Numeric(20)  NOT NULL,

  CONSTRAINT fk_cultivo FOREIGN KEY(ID_cultivo, ID_productor,ID_variedad) REFERENCES AJA_CULTIVO(ID, ID_productor, ID_variedad) ON DELETE CASCADE,
  CONSTRAINT pk_produccion_anual_real PRIMARY KEY(YYYY,ID_cultivo,ID_productor,ID_variedad)
);
INSERT INTO AJA_PRODUCCION_ANUAL_REAL VALUES
  (1,1,2, 2014,450000),
(2,1,3, 2005,750000),
(3,1,5, 2016,600000),
(4,2,7, 2010,300000),
(5,2,8, 2013,525000),
(6,2,9, 2006,375000),
(7,3,7,2003, 1700000),
(8,4,12, 2017,1300000),
(9,5,1, 2019, 24000000),
(10,5,3, 2021,18000000),
(11,6,6, 2018,27000000),
(12,6,7, 2014, 21000000),
(13,6,10,2012, 18000000),
(14,7,4,2010, 135000000),
(15,8,3, 2007, 165000000),
(16,9,1, 2002,1300000),
(17,9,3, 2001,1400000),
(18,9,6,2003,1000000),
(19,10,4, 2006,1400000),
(20,10,5, 2009, 1800000),
(21,11,4, 2019,11000000),
(22,12,14,2018,45500000);
--

CREATE TABLE AJA_CLIENTE(
  ID serial unique NOT NULL, 
  denominacion_comercial Varchar(100) unique  NOT NULL  , 
  mision TEXT  NOT NULL, 
  rango_inferior Numeric(3)  NOT NULL, 
  rango_superior Numeric(3)  NOT NULL, 
  porcentaje_aceptacion Numeric(2)  NOT NULL, 
  ID_ciudad integer  NOT NULL, 
  ID_pais_ciudad integer  NOT NULL,

  CONSTRAINT fk_ciudad FOREIGN KEY(ID_ciudad, ID_pais_ciudad) REFERENCES AJA_ciudad(ID,ID_pais),
  CONSTRAINT pk_cliente PRIMARY KEY(ID)
);
INSERT INTO AJA_CLIENTE (ID_ciudad, ID_pais_ciudad, denominacion_comercial, mision, rango_inferior, rango_superior, porcentaje_aceptacion) VALUES
(12,13, 'Tienda online de cerezas','Usted va a comprar un producto fresco, recolectado de forma artesanal por manos expertas procedentes de Cabezuela del Valle. Cáceres. Va a experimentar el maravilloso sabor de las mejores cerezas del mundo. Del árbol a su mesa en menos de 24 horas.',1,10,56),
(13,14, 'Cerezas de alicante','La montaña de Alicante tiene las condiciones climáticas y orográficas perfectas para que nuestras cerezas alcancen su punto máximo de sabor y color. Y desde nuestra cooperativa hacemos todo lo posible para que lleguen a tu mesa, estés donde estés. Incluso montar una tienda online como esta.',1,10,58),
(14,15, 'Ingredients Cellavent','Materias primas innovadoras para suplementos dietéticos, alimentos y productos farmacéuticos.',1,10,57),
(15,15, 'KIRSCHEN VON HIRSCHBERGER','Enviamos cerezas dulces frescas de lunes a jueves en julio. Recibirá nuestras crujientes cerezas premium heladas, clasificadas por tamaño en un joyero (capacidad 2 kg).',1,10,55),
(10,10, 'Cerezas Cosmetic','Cereza cosmetics es una empresa orgullosamente mexicana con la visión de ofrecerte productos cosméticos y de belleza con la mejor relación calidad precio.',1,10,52),
(16,7, 'Exotic Plants BG','Mantenemos una disponibilidad casi constante de alrededor de 300 especies de plantas en más de 850 variedades diferentes. Esto, a su vez, nos convierte en un líder en la vegetación rara y exótica para clima templado y subtropical en el territorio de Bulgaria.',1,10,51),
(17,6, 'TOUT BONBON','Golosinas en línea, entregadas en 48 horas, venta de dulces baratos y de alta calidad, tienda de dulces online de confianza',1,10,48),
(11,10, 'PricesSmart','Nuestra misión es servir como una empresa modelo, que opera de manera rentable y proporciona un buen rendimiento a nuestros inversores, proporcionando a los miembros en los mercados emergentes y en desarrollo mercancía emocionante y de alta calidad procedente de todo el mundo y servicios valiosos a precios atractivos. Priorizamos el bienestar y la seguridad de nuestros miembros y empleados. Proporcionamos buenos empleos, salarios justos y beneficios y la oportunidad de crecimiento. Nos esforzamos por tratar bien a nuestros proveedores y empoderarlos cuando podemos. Nos comportamos de manera socialmente responsable y respetamos el medio ambiente y las leyes de todos los países en los que operamos.',1,10,41),
(11,10, 'Fruandina','Fruandina trabaja permanentemente para lograr satisfacer las necesidades de los consumidores colombianos en cuanto al consumo de frutas frescas se refiere, manteniendo calidad, servicio y eficacia en sus procesos',1,10,64),
(16,7, 'Feinkost-kaefer','Calidad con pasión... es más que la impresión en nuestros envases en Käfer. Creemos en ella y vivimos este principio rector en cada una de nuestras actividades. La calidad de nuestros productos se caracteriza por la pasión con la que desarrollamos nuestro trabajo. Como ninguna otra compañía, nuestra marca ha defendido durante generaciones la afirmación de ofrecer siempre a sus clientes solo lo mejor.
A partir de años de experiencia y las crecientes demandas de nuestros clientes, los principios de calidad han crecido, que se consideran una guía vinculante en todas las áreas de la empresa. El Manifiesto de Calidad de Käfer es un conjunto vinculante de reglas para empleados y proveedores. Nuestro nombre es sinónimo de productos de la más alta calidad, servicio personal y técnicamente competente, creatividad, innovación, exclusividad y establecimiento de tendencias. El futuro de la marca Käfer radica en mantener y expandir esta reputación. Todos y cada uno de los empleados del Grupo Käfer contribuyen a ello.
',1,10,74);
--
CREATE TABLE AJA_VARIABLE(
  ID serial unique NOT NULL, 
  nombre Varchar(30)  NOT NULL, 
  descripcion text  NOT NULL, 
  tipo Varchar(20) NOT NULL,

  CONSTRAINT tipo_variable Check(tipo like 'individual' or tipo like 'variedad'),
  CONSTRAINT pk_variable PRIMARY KEY(ID)
);
INSERT INTO AJA_VARIABLE (nombre, descripcion, tipo) VALUES
  ('color','cumplir con el color establecido por su variedad, tanto en su piel como en su pulpa, en el caso de que no cumpla con el color se desecha la cereza','individual'),
  ('presentacion','debe tener forma ovoide o esférico, sus dimensiones, sabor, color, calibre pueden variar en función de su clase o variedad','individual'),
  ('sabor','define la cantidad de azucar que posee la cerezas, en el caso de que no tenga azucar es considerada acida','variedad'),
  ('calibre','tiene que tener un tamaño que ronde entre 20mm y 33mm, apartir de este rango se puede obtener un precio estimado de la cereza','individual'),
  ('firmeza','puede catalogarse en debil, media o fuerte, mientras sea mas firme, mayor valor tendra','variedad'),
  ('resistencia al agrario','Dependiendo de los valores del sabor, calibre y firmeza puede tener mayor o menos resistencia al cultivo','variedad'),
  ('zona cultivo','las mejores zonas para cultivar cerezas son en climas templados, es decir, zonas con temperaturas entre 10 y 15 grados','individual'),
  ('productividad','debe cumplir con la cantidad minima acordado con el productor y el cliente en el contrato','variedad'),
  ('Maduracion','debe tener un minimo de tiempo de tal modo que la cereza madure de forma satisfactoria, este tiempo puede variar de 3 a 9 meses segun la variedad de la cereza','variedad');
--

CREATE TABLE AJA_FORMULA(
  ID_cliente integer NOT NULL , 
  ID_variable integer NOT NULL, 
  ID serial unique NOT NULL, 
  tipo Varchar(20) NOT NULL,
  porcentaje_importancia Numeric(20)  NOT NULL,

  CONSTRAINT fk_cliente FOREIGN KEY(ID_cliente) REFERENCES AJA_CLIENTE(ID) ON DELETE CASCADE,
  CONSTRAINT fk_variable FOREIGN KEY(ID_variable) REFERENCES AJA_VARIABLE(ID) ON DELETE CASCADE,
  CONSTRAINT tipo_formula Check(tipo like 'dulce' or tipo like 'acida' or tipo like ''),   
  CONSTRAINT pk_formula PRIMARY KEY(ID,ID_cliente,ID_variable)
);
INSERT INTO AJA_FORMULA (ID_cliente, ID_variable, tipo, porcentaje_importancia) VALUES
(1,1, 'dulce',30),
(1,3, 'dulce',70),
(2,5, 'acida',41),
(2,7, 'acida',59),
(3,4, 'dulce',53),
(3,7,'dulce',47),
(4,4, 'dulce',50),
(4,3, 'dulce',30),
(5,1, 'dulce',25),
(5,8, 'acida',70),
(6,3, 'dulce',75),
(6,7, 'dulce',25),
(7,9, 'acida',36),
(7,4, 'acida',60),
(8,5, 'dulce',44),
(8,3, 'dulce',38),
(9,5, 'dulce',54),
(9,8, 'dulce',46),
(10,2, 'dulce',30),
(10,4, 'dulce',40),
(10,1, 'dulce',30);
--

CREATE TABLE AJA_METODO_DE_PAGO(
  ID_productor integer NOT NULL, 
  ID serial unique NOT NULL, 
  tipo Varchar(20) NOT NULL,
  cantidad_cuota Numeric(2), 
  porcentaje_cuota real, 
  contado_pago_contrato real, 
  contado_pago_envio real,

  CONSTRAINT tipo_metodo_pago Check(tipo like 'contado' or tipo like 'cuotas'),
  CONSTRAINT fk_productor FOREIGN KEY(ID_productor) REFERENCES AJA_PRODUCTOR(ID), 
  CONSTRAINT pk_metodo_pago PRIMARY KEY(ID,ID_productor)   
);
INSERT INTO AJA_METODO_DE_PAGO (ID_productor, tipo, cantidad_cuota, porcentaje_cuota,contado_pago_contrato, contado_pago_envio) VALUES 
  (10, 'contado',null,null,8463195,1493505),
  (9, 'contado',null,null,12316500,2173500),
  (6, 'cuotas',2,20,null,null),
  (8, 'contado',null,null,372574125,65748375),
  (10, 'cuotas',4,10,null,null),
  (10, 'cuotas',5,15,null,null),
  (14, 'contado',null,null,15249000,2691000),
  (1, 'contado',null,null,5865000,1035000),
  (11, 'cuotas',3,5,null,null),
  (5, 'contado',null,null,11260800,1987200),
  (14, 'contado',null,null,14410305,2542995),
  (2, 'cuotas',3,36,null,null),
  (12, 'cuotas',2,16,null,null),
  (4, 'contado',null,null,12707500,2242500);
--
CREATE TABLE AJA_CONTRATO(
  ID_cliente integer NOT NULL , 
  ID_productor integer NOT NULL,
  ID serial unique NOT NULL, 
  fecha_firma Date  NOT NULL, 
  tipo Varchar(20) NOT NULL, 
  estatus Varchar(20)  NOT NULL, 
  precio_contrato Numeric(10)  NOT NULL, 
  porcentaje_descuento Numeric(3),
  ID_metodo_pago integer,
  ID_productor_metodo_pago integer,
  fecha_cancelacion Date, 
  razon_cancelacion text,

  CONSTRAINT tipo_contrato Check(tipo like 'A' or tipo like 'T' or tipo like 'M'),
  CONSTRAINT estatus_contrato CHECK(estatus like 'activo' or estatus like 'cancelado'),
  CONSTRAINT fk_productor FOREIGN KEY(ID_productor) REFERENCES AJA_PRODUCTOR(ID), 
  CONSTRAINT fk_cliente FOREIGN KEY(ID_cliente) REFERENCES AJA_cliente(ID),  
  CONSTRAINT fk_metodo_pago FOREIGN KEY(ID_metodo_pago,ID_productor_metodo_pago) REFERENCES AJA_METODO_DE_PAGO(ID,ID_productor),  
  CONSTRAINT pk_contrato PRIMARY KEY(ID,ID_cliente,ID_productor)
);
INSERT into AJA_CONTRATO (ID_cliente, ID_productor ,fecha_firma, tipo, estatus, precio_contrato, porcentaje_descuento, ID_metodo_pago, ID_productor_metodo_pago, fecha_cancelacion, razon_cancelacion) VALUES
  (1,10, '2022-09-02','T','activo',9956700,37,1,10,null,null),
  (2,9, '2021-11-04','M','cancelado',14490000,5,2,9, '2021-12-23','No paso la evaluacion del cliente, se considera que esta por debajo de lo aceptado '),
  (3,6, '2022-01-25','M','activo',90321000,34,3,6,null,null),
  (4,8, '2021-08-01','T','activo',438322500,33,4,8,null,null),
  (5,10, '2022-10-13','A','activo',14490000,8,5,10,null,null),
  (6,10, '2021-12-11','A','cancelado',12730500,41,6,10, '2025-10-21','No paso la evaluacion del cliente, se considera que esta por debajo de lo aceptado '),
  (7,4, '2021-12-11','T','activo',17940000,7,7,14,null,null),
  (8,1, '2022-10-07','M','cancelado',6900000,12,8,1, '2020-10-31','No paso la evaluacion del cliente, se considera que esta por debajo de lo aceptado '),
  (9,11, '2022-10-01','A','cancelado',22137500,25,9,11, '2022-10-05','No paso la evaluacion del cliente, se considera que esta por debajo de lo aceptado '),
  (10,5, '2021-10-21','M','activo',13248000,06,10,5,null,null),
  (1,10, '2022-09-02','T','activo',16953300,63,11,14,null,null),
  (3,2, '2021-11-04','M','activo',1746562.5,45,12,2,null,null),
  (7,12, '2022-01-25','M','activo',104650000,8,13,12,null,null),
  (4,4, '2021-08-01','T','activo',14950000,2,14,4,null,null);
--
CREATE TABLE AJA_PAGO(
  ID_cliente integer NOT NULL , 
  ID_productor integer NOT NULL , 
  ID_contrato integer NOT NULL , 
  ID serial unique NOT NULL, 
  fecha Date  NOT NULL, 
  monto Numeric(20)  NOT NULL,

  CONSTRAINT fk_contrato FOREIGN KEY(ID_productor,ID_cliente,ID_contrato) REFERENCES AJA_CONTRATO(ID_productor,ID_cliente,ID) ON DELETE CASCADE,
  CONSTRAINT fk_pago PRIMARY KEY(ID_productor,ID_cliente,ID_contrato,ID)
);
INSERT into AJA_PAGO (ID_contrato,ID_cliente,ID_productor,fecha,monto) VALUES
  (1,1,10, '2022-11-02',1781),
  (2,2,9, '2022-01-04',2774),
  (3,3,6, '2022-02-25',2549.68),
  (3,3,6, '2022-05-25',2549.68),
  (4,4,8, '2021-09-01',2143),
  (5,5,10, '2020-11-10',1177.575),
  (5,5,10, '2020-12-10',1177.575),
  (5,5,10, '2021-01-10',1177.575),
  (5,5,10, '2021-10-10',1177.575),
  (6,6,10, '2020-12-11',820.228),
  (6,6,10, '2021-12-12',820.228),
  (6,6,10, '2021-02-13',820.228),
  (6,6,10, '2021-04-14',820.228),
  (6,6,10, '2021-06-15',820.228),
  (7,7,4, '2019-12-30',2430),
  (8,8,1, '2018-12-07',1776),
  (9,9,11, '2022-11-01',1610),
  (10,10,5, '2017-11-12',1229),
  (11,1,10, '2022-10-29',2412),
  (12,3,2, '2021-12-05',1136.4),
  (12,3,2, '2022-01-05',1136.4),
  (12,3,2, '2022-02-05',1136.4),
  (13,7,12, '2022-01-25',1625.525),
  (13,7,12, '2022-03-25',1625.525),
  (14,4,4, '2021-10-01',4199);
--
CREATE TABLE AJA_RENOVACION( 
  ID_secuencia serial NOT NULL, 
  ID_cliente integer NOT NULL, 
  ID_productor integer NOT NULL, 
  ID_contrato integer NOT NULL,
  fecha Date  NOT NULL, 
  total Numeric(20)  NOT NULL ,

  CONSTRAINT fk_contrato FOREIGN KEY(ID_productor,ID_cliente,ID_contrato) REFERENCES AJA_CONTRATO(ID_productor,ID_cliente,ID) ON DELETE CASCADE,
  CONSTRAINT pk_renovacion PRIMARY KEY(ID_productor,ID_cliente,ID_contrato,ID_secuencia,fecha)
);

INSERT into AJA_RENOVACION (ID_contrato,ID_cliente,ID_productor,fecha, total) values
  (1,1,10, '2023-05-31',11450205),
  (3,3,6, '2022-04-30',16373700),
  (4,4,8, '2022-05-31',105675570),
  (5,5,10, '2024-02-29',504070875),
  (7,7,4, '2020-12-31',17098200),
  (10,10,5, '2018-05-31',14385465),
  (11,1,10, '2023-01-31',21528000),
  (12,3,2, '2022-08-31',8211000),
  (13,7,12, '2024-02-29',25458125),
  (14,4,4, '2025-03-31',15367680);
--
CREATE TABLE AJA_VARIEDAD_EXPORTADA(
  ID_cliente integer NOT NULL,
  ID_productor integer NOT NULL,
  ID_contrato integer NOT NULL,
  ID_cultivo integer NOT NULL,
  ID_cultivo_productor integer NOT NULL,
  ID_cultivo_variedad integer NOT NULL,
  ID serial NOT NULL, 
  fecha_envio Date  NOT NULL, 
  cantidad Numeric(20)  NOT NULL, 
  descripcion text  NOT NULL, 
  porcentaje_descuento Numeric(20),

  CONSTRAINT fk_contrato FOREIGN KEY(ID_productor,ID_cliente,ID_contrato) REFERENCES AJA_CONTRATO(ID_productor,ID_cliente,ID), 
  CONSTRAINT fk_cultivo FOREIGN KEY(ID_cultivo,ID_cultivo_productor,ID_cultivo_variedad) REFERENCES AJA_CULTIVO(ID,ID_productor,ID_variedad),
  CONSTRAINT pk_variedad_exportada PRIMARY KEY(ID_productor,ID_cliente,ID_contrato,ID_cultivo,ID_cultivo_productor,ID_cultivo_variedad, ID)
);
insert into AJA_VARIEDAD_EXPORTADA(ID_cultivo,ID_cultivo_productor,ID_cultivo_variedad,ID_contrato,ID_cliente,ID_productor,fecha_envio,cantidad,descripcion,porcentaje_descuento) values
  (1,1,2,8,8,1, '2022-11-02',136400, 'La variedad  Ambrunés requiere ser exportada ',9.9),
  (4,2,7,12,3,2, '2022-01-04',7800, 'La variedad Marvin requiere ser exportada ',null),
  (8,4,12,14,4,4, '2022-03-25',13300, 'La variedad Kordia requiere ser exportada ',26.6),
  (9,5,1,10,10,5, '2021-10-01',100300, 'La variedad Van requiere ser exportada ',null),
  (11,6,6,3,3,6, '2020-12-13',37400, 'La variedad Bing requiere ser exportada ',12),
  (15,8,3,4,4,8, '2021-02-11',18000, 'La variedad Regina requiere ser exportada ',null),
  (18,9,6,2,2,9, '2020-02-11',87200, 'La variedad Bing requiere ser exportada ',27),
  (19,10,4,1,1,10, '2018-12-07',59900, 'La variedad Napoleon  requiere ser exportada ',null),
  (19,10,4,5,5,10, '2022-12-01',26800, 'La variedad Napoleon  requiere ser exportada ',14),
  (20,10,5,6,6,10, '2017-12-21',114200, 'La variedad Brulat requiere ser exportada ',27.6),
  (21,11,4,9,9,11, '2022-11-02',800, 'La variedad Napoleon  requiere ser exportada ',25),
  (22,12,14,13,7,12, '2022-01-04',116900, 'La variedad Marasca requiere ser exportada ',null);
--

CREATE TABLE AJA_ENVIOS_REALES(
  ID_cliente integer NOT NULL,
  ID_productor integer NOT NULL,
  ID_contrato integer NOT NULL,
  ID_cultivo integer NOT NULL,
  ID_cultivo_productor integer NOT NULL,
  ID_cultivo_variedad integer NOT NULL,
  ID_variedad_exportada integer NOT NULL,
  ID serial unique NOT NULL, 
  fecha_salida Date  NOT NULL, 
  peso Numeric(20)  NOT NULL, 
  fecha_recoleccion Date,

  CONSTRAINT fk_variedad_exportada FOREIGN KEY(ID_productor,ID_cliente,ID_contrato,ID_cultivo,ID_cultivo_productor,ID_cultivo_variedad,ID_variedad_exportada) REFERENCES AJA_VARIEDAD_EXPORTADA(ID_productor,ID_cliente,ID_contrato,ID_cultivo,ID_cultivo_productor,ID_cultivo_variedad,ID) ON DELETE CASCADE,
  CONSTRAINT pk_envios_reales PRIMARY KEY(ID_productor,ID_cliente,ID_contrato,ID_cultivo,ID_cultivo_productor,ID_cultivo_variedad,ID_variedad_exportada,ID)  
);
insert into AJA_ENVIOS_REALES(ID_variedad_exportada,ID_cultivo,ID_cultivo_productor,ID_cultivo_variedad,ID_contrato,ID_cliente,ID_productor,fecha_salida,peso,fecha_recoleccion)values
  (1,1,1,2,8,8,1, '2022-10-31',27280, '2022-10-30'),
  (1,1,1,2,8,8,1, '2022-01-02',54560, '2022-01-01'),
  (1,1,1,2,8,8,1, '2022-03-23',54560, '2022-03-22'),
  (2,4,2,7,12,3,2, '2022-02-04',7800, '2022-01-01'),
  (3,8,4,12,14,4,4, '2022-04-25',5320, '2022-03-22'),
  (3,8,4,12,14,4,4, '2021-11-01',7980, '2021-09-28'),
  (4,9,5,1,10,10,5, '2021-11-01',28657.1428571429, '2021-09-28'),
  (4,9,5,1,10,10,5, '2021-01-13',28657.1428571429, '2020-12-10'),
  (4,9,5,1,10,10,5, '2021-03-11',42985.7142857143, '2021-02-08'),
  (5,11,6,6,3,3,6, '2021-01-13',18700, '2020-12-10'),
  (5,11,6,6,3,3,6, '2021-03-11',18700, '2021-02-08'),
  (6,15,8,3,4,4,8, '2021-03-11',18000, '2021-02-08'),
  (7,18,9,6,2,2,9, '2020-03-11',29066.6666666667, '2020-02-08'),
  (7,18,9,6,2,2,9, '2019-01-07',58133.3333333333, '2018-12-04'),
  (8,19,10,4,1,1,10, '2019-01-07',19966.6666666667, '2018-12-04'),
  (8,19,10,4,1,1,10, '2023-01-01',39933.3333333333, '2022-11-28'),
  (9,19,10,4,5,5,10, '2023-01-01',13400, '2022-11-28'),
  (9,19,10,4,5,5,10, '2018-01-21',13400, '2017-12-18'),
  (10,20,10,5,6,6,10, '2018-01-21',32628.5714285714, '2017-12-18'),
  (10,20,10,5,6,6,10, '2022-12-02',32628.5714285714, '2022-10-30'),
  (10,20,10,5,6,6,10, '2022-02-04',48942.8571428571, '2022-01-01'),
  (11,21,11,4,9,9,11, '2022-12-02',800, '2022-10-30'),
  (12,22,12,14,13,7,12, '2022-02-04',23380, '2022-01-01'),
  (12,22,12,14,13,7,12, '2022-02-04',46760, '2022-01-01'),
  (12,22,12,14,13,7,12, '2022-02-04',46760, '2022-01-01');


--
CREATE TABLE AJA_RECETA(
  ID serial unique NOT NULL, 
  titulo Varchar(150)  NOT NULL, 
  tipo Varchar(20) NOT NULL, 
  raciones Varchar(20)  NOT NULL,
  tiempo_preparacion timestamp NOT NULL, 
  ID_cliente integer, 
  ID_productor integer,  

  CONSTRAINT tipo_RECETA Check( tipo like 'B' or tipo like 'P' or tipo like 'S' or tipo like 'Otro'),
  CONSTRAINT fk_productor FOREIGN KEY(ID_productor) REFERENCES AJA_productor(ID),
  CONSTRAINT fk_cliente FOREIGN KEY(ID_cliente) REFERENCES AJA_cliente(ID),
  CONSTRAINT pk_receta PRIMARY KEY(ID)
);
insert into AJA_RECETA(	titulo,	tipo,	raciones,tiempo_preparacion	,ID_cliente	,	ID_productor	) values
('Salmorejo de cerezas','S',6,to_date('15','mi'),10,null),
('Tarta de cerezas','P',6,to_date('50','mi'),null,1),
('Pastel de crema con salsa de cereza Bing','P',14,to_date('35','mi'),8,null),
('Ensalada de cereza Bing','P',10,to_date('10','mi'),null,5),
('Limonada de cerezas','B',6,to_date('15','mi'),2,null),
('Gazpacho de cerezas','S',6,to_date('25','mi'),10,null),
('Solomillo de cerdo con salsa de cerezas,','S',4,to_date('20','mi'),null,5),
('Chuletas de cerdo con salsa ahumada picante de cerezas','S',2,to_date('30','mi'),10,null),
('Polos de cereza y coco','P',4,to_date('10','mi'),4,null);
--
CREATE TABLE AJA_RESULTADO(
  ID_cliente integer NOT NULL, 
  ID_productor integer NOT NULL, 
  YYYY Date NOT NULL, 
  decision Varchar(20) NOT NULL, 
  resultado_valor Numeric(20)  NOT NULL, 
  resultado_porcentaje Numeric(20)  NOT NULL, 
  fecha Date  NOT NULL, 
  observacion text,  

  CONSTRAINT decision_resultado Check(decision like 'renueva' or decision like 'no renueva'),
  CONSTRAINT fk_productor FOREIGN KEY(ID_productor) REFERENCES AJA_productor(ID) ON DELETE CASCADE,
  CONSTRAINT fk_cliente FOREIGN KEY(ID_cliente) REFERENCES AJA_cliente(ID) ON DELETE CASCADE,
  CONSTRAINT pk_resultado PRIMARY KEY(ID_cliente,ID_productor,YYYY)
);
insert into AJA_RESULTADO values
  (1,10, to_date('2022','yyyy'),'renueva',8,80, '2022-04-30','el productor y cliente tuvieron una buena valoracion, de tal modo que hay altas probabilidades de que ambas partes renueven su contrato'),
  (2,9, to_date('2021','yyyy'),'no renueva',4,40, '2022-12-23','la valoracion del productor y cliente esta por debajo de la minima establecida por el contrato, de tal modo que habra renovacion'),
  (3,6, to_date('2022','yyyy'),'renueva',7,70, '2022-03-31','el cliente y el productor tuvieron un resultado aceptable en su contrato, por lo tanto existe una probabilidad de que existe alguna renovacion en su proximo contrato'),
  (4,8, to_date('2021','yyyy'),'renueva',6,60, '2022-04-30','ambas parte dieron una valoracion de aprobado minima, en consecuenca, no esta del todo claro si en un futuro pueda existir alguna renovacion por parte del cliente'),
  (5,10, to_date('2020','yyyy'),'renueva',8,80, '2022-01-31','el productor y cliente tuvieron una buena valoracion, de tal modo que hay altas probabilidades de que ambas partes renueven su contrato'),
  (6,10, to_date('2020','yyyy'),'no renueva',3,30, '2022-10-21','la valoracion del productor y cliente esta por debajo de la minima establecida por el contrato, de tal modo que habra renovacion'),
  (7,14, to_date('2019','yyyy'),'renueva',9,90, '2020-11-30','el cliente asigno una valoracion alta al productor, de tal modo que podemos decir que ambas partes van a hacer una renovacion casi segura'),
  (8,1, to_date('2018','yyyy'),'no renueva',4,40, '2020-10-31','la valoracion del productor y cliente esta por debajo de la minima establecida por el contrato, de tal modo que habra renovacion'),
  (9,11, to_date('2022','yyyy'),'no renueva',2,20, '2022-10-05','la valoracion del productor y cliente esta por debajo de la minima establecida por el contrato, de tal modo que habra renovacion'),
  (10,5, to_date('2017','yyyy'),'renueva',8,80, '2018-04-30','el productor y cliente tuvieron una buena valoracion, de tal modo que hay altas probabilidades de que ambas partes renueven su contrato'),
  (1,14, to_date('2022','yyyy'),'renueva',7,70, '2022-12-30','el cliente y el productor tuvieron un resultado aceptable en su contrato, por lo tanto existe una probabilidad de que existe alguna renovacion en su proximo contrato'),
  (3,2, to_date('2021','yyyy'),'renueva',9,90, '2022-07-30','el cliente asigno una valoracion alta al productor, de tal modo que podemos decir que ambas partes van a hacer una renovacion casi segura'),
  (7,12, to_date('2022','yyyy'),'renueva',10,100, '2021-01-31','el cliente dio una valoracion perfecta al productor, por lo tanto podemos decir con toda seguridad de que ambas partes van a hacer una renovacion futura'),
  (4,4, to_date('2021','yyyy'),'renueva',9,90, '2022-02-28','el cliente asigno una valoracion alta al productor, de tal modo que podemos decir que ambas partes van a hacer una renovacion casi segura');

--
CREATE TABLE AJA_ELABORACION(
  ID_receta integer NOT NULL, 
  numero_paso Numeric(20) NOT NULL, 
  descripcion text  NOT NULL,

  CONSTRAINT fk_receta FOREIGN KEY(ID_receta) REFERENCES AJA_RECETA(ID) ON DELETE CASCADE,
  CONSTRAINT pk_elaboracion PRIMARY KEY(ID_receta,numero_paso)
);
insert into AJA_ELABORACION values
  (1,1, 'Nuestra primera ocupación será picar los tomates y triturarlos. Si queremos poner pepino lo añadimos también a la batidora de vaso o el robot de cocina que utilicéis. No es imprescindible ya que luego vamos a colar el líquido para eliminar pepitas, trocitos de piel, y demás restos. Por ese motivo tampoco nos hace falta escaldar y pelar los tomates con antelación ni retirar sus pepitas.'),
  (1,2, 'Por otro lado, retiramos el pedúnculo y sacamos el pipo de las cerezas. Para ello podéis usar un cuchillo afilado o un instrumento específico para ello que os ayudará en esa tarea. (En el collage más abajo podéis ver cómo es este utensilio deshuesador). Añadimos las cerezas y las trituramos también. ciudado con las salpicaduras que el líquido es muy oscuro.'),
  (1,3, 'Añadimos el pan duro en trozos a la mezcla y dejamos que se empape bien de la misma y de paso se ablande. Incorporamos también el aceite de oliva y si queremos también el ajo. Trituramos el conjunto y sazonamos. Tiene que quedar una crema espesa gracias al aceite de oliva y el pan. Pasamos por el colador chino para retirar las pieles de tomate y de la cereza y lo llevamos a la nevera hasta el momento de servir.'),
  (2,1, 'Lava y deshuesa las cerezas.'),
  (2,2, 'Precalienta el horno a 180 grados.'),
  (2,3, 'En un bol, mezcla los huevos, el azúcar, la sal, y mezcla.'),
  (2,4, 'Añade la harina tamizándola poco a poco y mezclando bien para que quede una masa homogénea.'),
  (2,5, 'Derrite 40 gramos de mantequilla (el resto lo reservas) y la añades a la mezcla.'),
  (2,6, 'Vierte la leche y remueve hasta que los ingredientes queden integrados.'),
  (2,7, 'Unta un molde con mantequilla, y vierte dos tercios de la mezcla.'),
  (2,8, 'Reparte las cerezas por encima de la masa.'),
  (2,9, 'Vierte suavemente el resto de la mezcla sobre las cerezas.'),
  (2,10, 'Cubre la tarta con el resto de mantequilla derretida.'),
  (2,11, 'Hornea a 180º durante 40 minutos. (Antes de retirar del horno haz la prueba del palillo).'),
  (2,12, 'Dejar enfriar y espolvorea con azúcar glas.'),
  (3,1, 'Prepare y hornee la mezcla para pastel de acuerdo con las instrucciones del fabricante para dos capas de 9 pulgadas.'),
  (3,2, 'Mientras se hornea el pastel, haz el relleno. Pon la leche en una cacerola a fuego medio. mezcle el azúcar, la sal y la maicena, mezcle con la leche. Agregue el huevo y hierva la mezcla, revolviendo constantemente. Cuando la mezcla se espese, retire del fuego y agregue la mantequilla y la vainilla. Vierta en un plato. Coloque una envoltura de plástico directamente sobre la superficie del pudín y refrigere hasta que se enfríe.'),
  (3,3, 'Mientras se hornea el pastel, haz el relleno. Pon la leche en una cacerola a fuego medio. mezcle el azúcar, la sal y la maicena, mezcle con la leche. Agregue el huevo y hierva la mezcla, revolviendo constantemente. Cuando la mezcla se espese, retire del fuego y agregue la mantequilla y la vainilla. Vierta en un plato. Coloque una envoltura de plástico directamente sobre la superficie del pudín y refrigere hasta que se enfríe.'),
  (3,4, 'Mientras se hornea el pastel, haz el relleno. Pon la leche en una cacerola a fuego medio. mezcle el azúcar, la sal y la maicena, mezcle con la leche. Agregue el huevo y hierva la mezcla, revolviendo constantemente. Cuando la mezcla se espese, retire del fuego y agregue la mantequilla y la vainilla. Vierta en un plato. Coloque una envoltura de plástico directamente sobre la superficie del pudín y refrigere hasta que se enfríe.'),
  (3,5, 'Mientras se hornea el pastel, haz el relleno. Pon la leche en una cacerola a fuego medio. mezcle el azúcar, la sal y la maicena, mezcle con la leche. Agregue el huevo y hierva la mezcla, revolviendo constantemente. Cuando la mezcla se espese, retire del fuego y agregue la mantequilla y la vainilla. Vierta en un plato. Coloque una envoltura de plástico directamente sobre la superficie del pudín y refrigere hasta que se enfríe.'),
  (4,1, 'Escurre el jugo de piña y el agua de cereza en una taza medidora de 2 tazas. Vierta suficiente color para hacer 2 tazas; deseche la soda restante. Vierta la mezcla de cola en una cacerola.'),
  (4,2, 'Lleve la mezcla de cola a ebullición a fuego medio-alto. Retire del fuego, luego agregue la piña, las cerezas, el queso crema y la gelatina de cereza hasta que la gelatina se disuelva, aproximadamente 5 minutos. Vierta la mezcla en un molde o fuente de vidrio para hornear. Refrigere hasta que cuaje, aproximadamente 3 horas.'),
  (5,1, 'Ponga las cerezas, limones, azúcar y 2 tazas de agua en la licuadora. También puede agregar un poco de hielo si lo quiere bien frio (pero igual se sirve con hielo al final).'),
  (5,2, 'Licue todos los ingredientes hasta que estén bien triturados.'),
  (5,3, 'Cierne o cole la mezcla a licuada y luego añada el resto del agua.'),
  (5,4, 'Sirva la limonada de cerezas con bastante hielo y adornado con rodajas de limón y cerezas.'),
  (6,1, 'En la batidora de vaso, ponemos los ingredientes indicados, una vez remojado el pan en el agua, y habiendo quitado el hueso de las cerezas. Lo batimos y trituramos a conciencia hasta obtener una crema bien fina.'),
  (6,2, 'Para terminar nuestra elaboración, pasamos el gazpacho por un colador bien fino ayudándonos de un cucharón. En el colador quedarán las pieles y pepitas de tomate y pimiento y el gazpacho de cerezas será más ligero.'),
  (6,3, 'Una vez preparado el gazpacho de cerezas, lo probamos y rectificamos si fuera necesario, añadiendo más agua si no lo queremos espeso o completando con un poco más de vinagre, de aceite y de sal. Dejamos reposar en la nevera al menos 30 minutos para servirlo bien frío.'),
  (7,1, 'El primer paso para preparar el solomillo de cerdo con salsa de cerezas es marinar la carne. Para ello mezclamos el zumo de una de las naranjas y el de medio limón con dos cucharadas de miel y un chorrito de agua. Llevamos a ebullición y pintamos los solomillos con esta mezcla. Dejamos marinar unas horas.'),
  (7,2, 'Por otro lado, lavamos y deshuesamos las cerezas, que colocaremos en una cazuela junto con el zumo de la otra naranja y de la otra mitad del limón. Añadimos también un chorrito de whisky y dos cucharadas de miel. A fuego lento, vamos reduciendo la salsa hasta que haya quedado de una consistencia espesa.'),
  (7,3, 'Por último, cortamos los solomillos en medallones, pasamos la carne por la plancha, ajustamos de sal y pimienta y servimos los medallones de solomillo junto con la salsa.'),
  (8,1, 'Lavar bien las cerezas o picotas, secarlas y quitar el hueso. Trocerlas groseramente. Poner a calentar un poco de aceite de oliva en una sartén y añadir la fruta, dando unas vueltas a fuego fuerte. Agregar una pizca de sal, el vinagre, la miel y el limón, y remover. Bajar el fuego, tapar y dejar cocer unos 10-15 minutos, hasta que estén muy blandas y hayan soltado jugos.'),
  (8,2, 'Retirar del fuego y dejar enfriar un poco. Colocar en el vaso de la batidora, añadiendo bien los jugos, e incorporar el chile y un poco de la salsa de su adobo. Triturar muy bien hasta dejar una textura homogénea y fina. Si lo preferimos, podemos pasarla por un colador o chino.'),
  (8,3, 'Probar el punto y añadir la nata espesa al gusto para rebajar el picante, si estuviera muy fuerte. Reservar. Cocinar a la plancha o a la parrilla las chuletas de cerdo, salpimentándolas y sazonándolas con un poco de tomillo y ajo granulado. Retirar y servir con la salsa, añadiendo perejil picado por encima.'),
  (9,1, 'La receta no podría ser más sencilla, aunque se puede variar un poco al gusto. Las cantidades dependerán del tamaño de nuestros moldes y del número de polos que queramos elaborar. Es conveniente que tanto el agua de coco como las cerezas estén muy fríos.'),
  (9,2, 'Lavamos las cerezas, las secamos bien y las partimos, quitando el hueso. Las trituramos con una picadora, trituradora o similar, hasta tener una textura de puré de fruta. A mí me gusta sentir la fruta en el polo, pero se puede pasar por un chino para dejarlo más fino si se desea. Probar el punto de dulce y añadir azúcar o edulcorante al gusto si se desea, así como vainilla.'),
  (9,3, 'Llenar 1-2 dedos de la base de los moldes de polo con el puré de cerezas. Llevar al congelador y esperar a que endurezca, unos 30-45 minutos. Echar otra capaa de agua de coco y repetir la operación alternando con las cerezas hasta llenar el molde. Introducir los palitos y congelar por lo menos 1 hora antes de desmoldar.');
--
CREATE TABLE AJA_INGREDIENTE(
  ID serial unique NOT NULL, 
  nombre Varchar(40)  NOT NULL, 
  descripcion text  NOT NULL,
  
  CONSTRAINT pk_ingrediente PRIMARY KEY(ID)
);
insert into AJA_INGREDIENTE(nombre,descripcion) VALUES
  ('Tomate','Baya roja, fruto de la tomatera, de superficie lisa y brillante, en cuya pulpa hay numerosas semillas algo aplastadas y amarillas'),
  ('Pan de hogaza','Pan grande, hecho con harinas especiales como la candeal u otras ricas en gluten, que consiguen una miga compacta, sabrosa y de larga duración. La fermentación de este tipo de pan ha de ser lenta, reposada, si es posible en armario de madera, sin forzar por calor pero manteniendo la temperatura para que no pierda el equilibrio necesario.'),
  ('Aceite de oliva virgen extra','El aceite de oliva virgen extra es, dentro de los aceites de oliva vírgenes, la categoría de aceite de oliva de mayor calidad.'),
  ('Sal','Es el encargado de proporcionar a los alimentos el toque salado como bien indica su nombre. Tiene la propiedad de modificar y alterar el sabor de los alimentos a los que se le aplica, intensificándolos. Se puede encontrar en varios formatos disponibles en cualquier supermercado además de poder ser utilizado como conservante.'),
  ('Ajo','Es el encargado de proporcionar a los alimentos el toque salado como bien indica su nombre. Tiene la propiedad de modificar y alterar el sabor de los alimentos a los que se le aplica, intensificándolos. Se puede encontrar en varios formatos disponibles en cualquier supermercado además de poder ser utilizado como conservante.'),
  ('Huevos','El huevo de gallina es un alimento de origen animal con muchas propiedades nutricionales y culinarias. Se caracteriza por su calidad nutritiva, ser una proteína de bajo costo y un ingrediente muy habitual en la alimentación humana.'),
  ('Mantequilla','Un derivado lácteo con un elevado contenido graso, derivado exclusivamente de la leche o de determinados productos lácteos, en forma de emulsión sólida principalmente del tipo agua en materia grasa.'),
  ('Azucar','El azúcar es una sustancia de sabor dulce y color blanco, cristalizada en pequeñísimos granos'),
  ('Azucar Glass','El azúcar glas es la misma azúcar blanca refinada pero pulverizada. Los cristales de la versión tradicional de mesa son molidos hasta hacerlos impalpables.'),
  ('Harina de trigo','La harina de trigo es un polvo hecho de la molienda del trigo y que se emplea para consumo humano. La harina de trigo es la que más se produce de entre todas las harinas.'),
  ('Leche liquida','Liquido comunmente proveniente de vacas y cualquier mamifero'),
  ('Mezcla para pastel blanco','Una mezcla para pasteles es una mezcla de harina, levadura, saborizantes y otros ingredientes que se han medido de forma que solo se necesitan agregar algunos ingredientes frescos para hornear un pastel. Por lo general, los ingredientes necesarios son huevos, aceite o mantequilla, y agua o leche.'),
  ('Maicena','Harina fina de maíz.'),
  ('Extracto de vainilla','El extracto de vainilla, como bien su nombre lo indica es un concentrado - que se utiliza para saborizar comidas y bebidas - obtenido de la vaina o chaucha de la vainilla (género de orquídeas que produce un fruto del cual se obtiene este saborizante, después de un sencillo proceso de maceración)'),
  ('Crema espesa','La crema espesa son los productos lácteos que se usan en dulces, postres y otras cocinas, sobre todo para el final. Es muy similar a la crema de leche casera u otras cremas simples que se obtienen al desnatar la capa alta en grasa de la parte superior de la leche.'),
  ('Chocolate semidulce','El chocolate semidulce es una subcategoría del chocolate negro. El chocolate negro debe tener un mínimo de 35% de sólidos de cacao. Cuanto mayor sea el número de sólidos, más amargo será el chocolate. El chocolate semidulce tiene un contenido de sólidos de cacao de entre el 35 y el 65%.'),
  ('Agua','Líquido transparente, incoloro, inodoro e insípido en estado puro, cuyas moléculas están formadas por dos átomos de hidrógeno y uno de oxígeno, y que constituye el componente más abundante de la superficie terrestre y el mayoritario de todos los organismos vivos. (Fórmula H2O).'),
  ('Piña triturada ','Piña Machacada en Lata en Jugo de Piña 100%. Piña dorada finamente triturada.'),
  ('Queso crema ','El queso crema es un tipo de queso untable que se obtiene al cuajar mediante fermentos lácticos una mezcla de leche y nata.'),
  ('Bebida carbonatada sabor a cola','Bebida gaseosa en la que se utiliza comunmente la nuez de cola, fruto de un árbol que lleva el mismo nombre.'),
  ('Cereza Jell-O','Jello es la marca de gelatina en Estados Unidos. De hecho, muchos llaman Jello a la gelatina en general, es distinguido por su sabor a cereza y su fácil preparación.'),
  ('Limones sutiles','Citrus aurantifolia Variedad conocida en algunos lugares como Limón Sutil. Es productiva, de frutos pequeños, redondeados, de corteza lisa y fina, de color amarillo verdoso, con semillas, muy jugosa y ácida.'),
  ('Hielo','Agua congelada '),
  ('Pimiento verde italiano','Se caracteriza por producir frutos alargados, puntiagudos de 5 cm. de ancho y una longitud de 18 cm. De carne delgada, sabor dulce, piel fina y color verde brillante que se vuelve rojo en su total madurez.'),
  ('Cebolla','planta de huerta que pertenece al grupo familiar de las liliáceas y se caracteriza por desarrollar un bulbo compuesto por capas sucesivas que es comestible. tiene un sabor algo picante y un aroma muy intenso y característico. Su ingesta contribuye a combatir los efectos del reumatismo, a prevenir la osteoporosis y las infecciones y a proteger el sistema cardiovascular. Esto se debe que cuenta con potasio, calcio, silicio y fósforo, entre otros nutrientes.'),
  ('Vinagre de manzana','es un tipo de vinagre elaborado de la fermentación de los azúcares, ya sea del jugo de manzana o de sidra. La fermentación aeróbica suele convertirse en ácido málico o en ácido acético, Se ha utilizado tradicionalmente para la limpieza y la desinfección, el tratamiento del hongo de las uñas, los piojos, las verrugas y las infecciones del oído.'),
  ('Solomillo de cerdo','es un corte de cerdo largo y delgado. El solomillo es una pieza de carne procedente de la parte lumbar, alojado entre las costillas inferiores y la columna vertebral; más concretamente, encima de los riñones y debajo del lomo bajo'),
  ('Naranja','La naranja es un fruto redondo, color naranja, consumido mayoritariamente en invierno. La pulpa del interior es también anaranjada y está formada por pequeñas bolsitas llenas de zumo. Las naranjas frescas son bajas en calorías y fuente de fibra, potasio, vitamina C y folato. Ayudan a prevenir el cáncer y las enfermedades cardiovasculares.'),
  ('Miel','es un alimento nutritivo, saludable y natural producido por las abejas. Sus propiedades benéficas van mas allá del uso como dulcificante, ya que es rico en sales minerales, enzimas, vitaminas y proteínas que le donan propiedades nutritivas y organolépticas únicas.'),
  ('Whisky','el whisky es una bebida que se obtiene de la destilación de la fermentación de los granos de cereal molido y que se añeja en barriles de madera. Por lo general, su grado alcohólico varía entre los 35 y 50 grados. El whiskey está hecho con granos malteados que contienen por lo menos 51 por ciento de maíz. Consiste en maíz, agua, malta de cebada, trigo o centeno.'),
  ('Pimienta negra molida','es una de las especias básicas de la cocina española junto con el pimentón dulce. Este condimento aporta a las comidas un sabor picante que combina a la perfección con cualquier plato, aunque destaca sobre todo como sazonador para la carne.'),
  ('Chuletas de lomo de cerdo','La chuleta es un corte especializado de la parte superior del costillar del cerdo o del espinazo. Suele ser grande, lo suficiente para servir una chuleta de lomo por persona. Como es parte del lomo, contiene muy poco colesterol.'),
  ('Vinagre de Módena','Se trata de un vinagre obtenido a partir de una mezcla de vinos, en la que intervienen vinos tintos y vinos blancos, los cuales han sido producidos a partir de uvas de las variedades: trebbiano, uniblanc, malbec o barbera. Dentro de sus características se destaca un sabor fuerte y ligeramente dulce, y su color oscuro.'),
  ('Melaza','esiduo de cristalización nal del azúcar, del que no se puede obtener más azúcar por métodos físicos. Se elabora mediante la cocción del jugo de la caña de azúcar hasta la evaporación parcial del agua que éste contiene, formándose un producto meloso semi-cristalizado.'),
  ('Zumo de limón','es el líquido obtenido del endocarpio de los limones al ser exprimido. Dicho líquido suele representar un 30% del peso del fruto. Se suele extraer de forma casera directamente de los limones, aunque los hay también envasados o en forma de extractos.'),
  ('Chile chipotle en adobo','son chiles jalapeños secos, ahumados y adobados en una deliciosa salsa, hecha de tomate y especias. Se usan como ingrediente para agregar un sabor ahumado y picante a casi todas las recetas, desde mariscos sopas, salsas, marinadas, hamburguesas, hasta asados.'),
  ('Creme Fraiche','Es una crema de leche, entre blanca y amarillo pálido, con un contenido en grasa de 30–40 % y un 15% de lactosa, y ligeramente acidificada por los cultivos bacterianos que contiene.'),
  ('Tomillo','se emplea desde hace siglos para aromatizar licores y aceites, para sazonar guisos, como insecticida, o para elaborar perfumes. Pero esta planta también tiene aplicaciones terapéuticas.'),
  ('Perejil','planta está provista de tallos erguidos, tubulares que pueden alcanzar 70 cm de altura y raíz pivotante abultada y carnosa. Las hojas, con largos pecíolos, son dentadas y subdivididas en tres segmentos y de forma más o menos triangular.'),
  ('Agua de coco','es el líquido que se encuentra de forma natural en el hueco interior del coco.​​ Tiene un color transparente, a veces un poco opaco, y se encuentra en el hueco interior, rodeado por la pulpa del coco, en la nuez del coco.');
--
CREATE TABLE AJA_MEDIDAS_DEL_INGREDIENTE(
  ID_receta integer NOT NULL, 
  ID_ingrediente integer NOT NULL, 
  cantidad real NOT NULL, 
  medida Varchar(20),

  CONSTRAINT fk_receta FOREIGN KEY(ID_receta) REFERENCES AJA_RECETA(ID) ON DELETE CASCADE,
  CONSTRAINT fk_ingrediente FOREIGN KEY(ID_ingrediente) REFERENCES AJA_ingrediente(ID) ON DELETE CASCADE,
  CONSTRAINT pk_medida_ingrdiente PRIMARY KEY(ID_receta,ID_ingrediente)
);
insert into AJA_MEDIDAS_DEL_INGREDIENTE VALUES
  (1,1,650, 'g'),
  (1,2,200, 'g'),
  (1,3,150, 'g'),
  (1,4,1, 'Al gusto'),
  (1,5,0.5, 'Dientes de ajo'),
  (2,6,6,null),
  (2,7,70, 'g'),
  (2,8,180, 'g'),
  (2,9,20, 'g'),
  (2,10,100, 'g'),
  (2,11,250, 'ml'),
  (3,12,517, 'g'),
  (3,11,2, 'Tazas'),
  (3,8,1/3, 'Taza'),
  (3,4,2, 'Cucharadas'),
  (3,13,3, 'Cucharadas'),
  (3,6,1,null),
  (3,7,1, 'Cucharadas'),
  (3,14,5/2, 'Cucharadas'),
  (3,15,1, 'Pinta'),
  (3,16,57, 'g'),
  (3,17,1/4, 'Taza'),
  (4,18,1, 'Lata'),
  (4,19,85, 'g'),
  (4,20,1, 'Latas'),
  (4,21,1, 'Paquetes'),
  (5,8,1, 'Taza'),
  (5,17,6, 'Tazas'),
  (5,22,2,null),
  (5,23,1, 'Al Gusto'),
  (6,1,500, 'g'),
  (6,24,50, 'g'),
  (6,25,50, 'g'),
  (6,2,100, 'g'),
  (6,3,100, 'ml'),
  (6,26,20, 'ml'),
  (6,17,200, 'ml'),
  (7,27,2,null),
  (7,28,2,null),
  (7,22,1,null),
  (7,29,4, 'Cucharadas'),
  (7,30,1, 'Chorritos'),
  (7,4,1, 'Al Gusto'),
  (7,31,1, 'Al Gusto'),
  (8,32,6,null),
  (8,33,5, 'ml'),
  (8,34,15, 'ml'),
  (8,35,2, 'ml'),
  (8,36,1, 'Al Gusto'),
  (8,37,30, 'ml'),
  (8,5,1, 'Granulado'),
  (8,38,1,null),
  (8,31,1, 'Al Gusto'),
  (8,4,1, 'Al Gusto'),
  (8,3,1, 'Al Gusto'),
  (8,39,1,null),
  (9,40,330, 'ml'),
  (9,8,1, 'Al Gusto'),
  (9,14,2, 'ml');
--
CREATE TABLE AJA_VARIEDAD_POR_RECETA(
  ID_receta integer NOT NULL, 
  ID_variedad integer NOT NULL, 
  cantidad Numeric(20)  NOT NULL,

  CONSTRAINT fk_receta FOREIGN KEY(ID_receta) REFERENCES AJA_RECETA(ID) ON DELETE CASCADE,
  CONSTRAINT fk_variedad FOREIGN KEY(ID_variedad) REFERENCES AJA_VARIEDAD_DE_CEREZAS(ID) ON DELETE CASCADE,
  CONSTRAINT pk_variedad_por_receta PRIMARY KEY(ID_receta,ID_variedad)
);
insert into AJA_VARIEDAD_POR_RECETA values 
  (1,8,350),
  (2,4,500),
  (3,6,425),
  (4,6,425),
  (5,4,330),
  (6,3,250),
  (7,11,200),
  (8,9,200),
  (9,8,200);
--
CREATE TABLE AJA_PADRINO(
  ID serial unique unique NOT NULL, 
  documento_identidad Numeric(20) unique NOT NULL  , 
  primer_nombre Varchar(20)  NOT NULL, 
  primer_apellido Varchar(20)  NOT NULL, 
  segundo_apellido Varchar(20)  NOT NULL, 
  segundo_nombre Varchar(20),

  CONSTRAINT pk_padrino PRIMARY KEY(ID,documento_identidad)
);
insert into aja_padrino (documento_identidad,primer_nombre,primer_apellido,segundo_apellido,segundo_nombre) values
  (1030758874, 'Sigismondo','O Neil','Gertruda','Covino'),
  (402718897, 'Lucius','Stewartson','Candida','Budgen'),
  (1189431494, 'Moria','Dighton','Ange','von Grollmann'),
  (1159101077, 'Yale','Rosenzveig','Randolf','Camilletti'),
  (867792579, 'Rosy','Marcone','Sandye','Lorincz'),
  (1175837449, 'Spence','O Fihillie','Trev','Yurshev'),
  (1441813057, 'Jana','Abrahamowitcz','Aurora','Hanham'),
  (679470574, 'Haley','Meugens','Alain','Woodfine'),
  (1209024031, 'Gilburt','Treamayne','Vince','Gradley');
--
CREATE TABLE AJA_PROGRAMA_DE_APADRINAMIENTO(
  ID_padrino integer NOT NULL, 
  ID_productor integer NOT NULL, 
  ID_variedad integer NOT NULL, 
  fecha_inicio Date  NOT NULL, 
  descripcion text  NOT NULL, 
  fecha_fin Date, 
  aporte_anual integer,

  CONSTRAINT fk_padrino FOREIGN KEY(ID_padrino) REFERENCES AJA_PADRINO(ID) ON DELETE CASCADE,
  CONSTRAINT fk_productor FOREIGN KEY(ID_productor) REFERENCES AJA_PRODUCTOR(ID) ON DELETE CASCADE,
  CONSTRAINT fk_variedad FOREIGN KEY(ID_variedad) REFERENCES AJA_VARIEDAD_DE_CEREZAS(ID) ON DELETE CASCADE,
  CONSTRAINT pk_programa_apadrinamiento PRIMARY KEY(ID_padrino,ID_productor,ID_variedad)
);

insert into AJA_PROGRAMA_DE_APADRINAMIENTO VALUES
  (2,10,13, '2021-09-11','El programa tiene como objetivo enseñar a cultivar cerezas regina en nuevos terrenos de Francia','2025-12-11',324),
  (3,5,3, '2017-09-21','crear nuevos cultivos en el norte de estados unidos para producir mayor cantidad de cerezas lapins','2020-06-21',196),
  (7,7,2, '2018-03-10','programa tiene como meta principal la cultivacion de nuevas cerezas ambrunes a lo largo del territorio de chile','2021-05-10',204),
  (7,8,13, '2018-09-25','programa tiene como meta principal la cultivacion de nuevas cerezas regina a lo largo del territorio de chile','2021-11-25',388),
  (8,5,1, '2019-10-25','crear nuevos cultivos en el norte-oeste de estados unidos para aumentar la produccion de cerezas de van','2022-11-25',103),
  (9,4,2, '2020-11-25','expandir el terreno en el sur de italia para producir mas cerezas de tipo ambruna','2022-08-25',413),
  (3,9,1, '2020-07-25','el programa tiene como meta cultivar cerezas de tipo van a lo largo del territorio sur de canada','2023-09-25',282),
  (6,11,6, '2017-05-20','cultivar cerezas bing en todo el territorio aleman','2018-07-20',148),
  (3,5,5, '2017-04-07','crear nuevos cultivos burlat en el norte de estados unidos para producir mayor cantidad de cerezas lapins','2020-09-07',470),
  (2,7,3, '2021-03-03','programa tiene como meta principal la cultivacion de nuevas cerezas lapins a lo largo del territorio de chile','2023-09-03',433),
  (1,12,3, '2021-04-26','el programa busca cultivar productos de cerezas lapins en el pais argentino','2023-11-26',220),
  (5,8,12, '2019-09-29','programa tiene como meta principal la cultivacion de nuevas cerezas kordia a lo largo del territorio de chile','2023-12-29',121),
  (7,1,11, '2018-09-16','El programa tiene como objetivo enseñar a cultivar cerezas  celeste en nuevos terrenos de España','2021-04-16',389),
  (5,10,1, '2017-06-08','crear nuevos cultivos en el sur de francia para producir mayor cantidad de cerezas van','2018-11-08',81),
  (7,9,12, '2018-09-28','el programa tiene como meta cultivar cerezas de tipo kordia a lo largo del territorio sur de canada','2021-02-28',194);
--

create table aja_beneficios( 
	id_beneficios serial not null,
	descripcion text not NULL,
	constraint pk_beneficios PRIMARY KEY(id_beneficios)
);

insert into aja_beneficios (descripcion)
values ('
Cereza Rainer (Prunus avium var, rainier) Valor nutricional por cada 100 g
Energia 62 kcal 260 kJ

Carbohidratos-------------------------------16.01 g

-Azúcares	----------------------------------12.82 g

Fibra alimentaria--------------------------- 2.10 g

Grasas-------------------------------------- 0.20 g

saturadas-----------------------------------0.038

monoinsaturadas  -------------------------- 0.047

-polinsaturadas  -------------------------- 0.052 

Proteinas  ----------------------------------1.06 g 

Agua  --------------------------------------82.25 g

Retinol (vit. A)  ----------------------------- 3 µg (0%)

Tiamina (vit. B)  ------------------------- 0.027 mg (2%)

Riboflavina (vit. 82)  ---------------------0.033 mg (2%)

Niacina (vit. B3)  -------------------------0.154 mg (1%)

Acido pantoténico (vit. Bs)  ---------------0.199 mg (4%)

Vitamina Be  -------------------------------0.049 mg (4%)

Acido fólico (vit. Bg)  ----------------------- 4 µg (1%)

Vitamina C  ----------------------------------- 7 mg (12%) 

Vitamina E  ---------------------------------0.07 mg (0%)

Vitamina K  ---------------------------------2.10 µg (2%)

Calcio  ---------------------------------------13 mg (1%)

Hierro  -------------------------------------0.36 mg (3%)

Magnesio  -------------------------------------11 mg (3%)

Manganeso  --------------------------------- 0.07 mg (4%)

Fósforo  ------------------------------------- 21 mg (3%)

Potasio  -------------------------------------222 mg (5%)

Zinc  ---------------------------------------0.07 mg (1%)

  % de la cantidad diaria recomendada para adultos.

  Fuente: Cereza dulce (http://ndb.nal.usda.gov/ndb/search!ist?qlookup-09070&format=Full) en la base de datos de nutrientes de USDA
  ');

delete from aja_envios_reales evr
where evr.id_productor= 11;

delete from aja_variedad_exportada vx
where vx.id_productor= 11;

delete from aja_cultivo cul
where cul.id_productor= 11;

delete from aja_contrato con 
where con.id_productor= 11;

delete from aja_trabajan tr
where tr.id_productor= 11;

delete from aja_metodo_de_pago mtd
where mtd.id_productor= 11;

delete from aja_productor pd
where pd.id=11;

-- Mas de una variedad para contratos 1 y 8 solo se puede ejecutar una vez por cada contrato especificado 
INSERT INTO aja_variedad_exportada
(id_cliente, id_productor, id_contrato, id_cultivo, id_cultivo_productor, id_cultivo_variedad, 
 fecha_envio, cantidad, descripcion, porcentaje_descuento)
(select  cli.id, pd.id, con.id, 
 (select id from aja_cultivo vo where vo.id_productor= pd.id and vo.id_variedad<>var.id limit 1),
 (select id_productor from aja_cultivo vo where pd.id= vo.id_productor and vo.id_variedad<>var.id limit 1),
 (select id_variedad from aja_cultivo vo where pd.id= vo.id_productor and vo.id_variedad<>var.id limit 1), 
 (select fecha_envio from aja_variedad_exportada where id= vx.id), 
 (select cantidad from aja_variedad_exportada where id= vx.id), 
 (select 'La variedad '||vdc.nombre||' requiere ser exportada ' from aja_variedad_de_cerezas vdc, aja_cultivo ivo where ivo.id_variedad= vdc.id and vdc.id<> var.id and ivo.id_productor= pd.id limit 1), 
 (select porcentaje_descuento from aja_variedad_exportada where id= vx.id+2)
from aja_cultivo cul, aja_contrato con, aja_cliente cli, aja_productor pd, aja_variedad_de_cerezas var, aja_variedad_exportada vx
where con.id_cliente= cli.id and con.id_productor= pd.id
and cul.id_productor= pd.id and cul.id_variedad= var.id
and vx.id_productor= con.id_productor and vx.ID_cultivo_variedad= cul.id_variedad
 and vx.id_contrato= con.id and vx.id_cliente= con.id_cliente 
 and vx.id_productor = con.id_productor and vx.ID_cultivo_productor= cul.id_productor
and (con.id= 1 or con.id= 8));

