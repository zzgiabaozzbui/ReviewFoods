-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 15, 2022 lúc 08:02 PM
-- Phiên bản máy phục vụ: 10.4.19-MariaDB
-- Phiên bản PHP: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `reviewfood`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cauhoi`
--

CREATE TABLE `cauhoi` (
  `id` int(11) NOT NULL,
  `cauhoi` text NOT NULL,
  `traloi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhdau`
--

CREATE TABLE `danhdau` (
  `idmonan` int(11) NOT NULL,
  `tentaikhoan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `danhdau`
--

INSERT INTO `danhdau` (`idmonan`, `tentaikhoan`) VALUES
(1, 'thangyb27'),
(21, 'thangyb27'),
(4, 'thangyb27'),
(4, 'null'),
(21, 'null');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhgia`
--

CREATE TABLE `danhgia` (
  `idmonan` int(11) NOT NULL,
  `tentaikhoan` varchar(50) NOT NULL,
  `danhgia` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `danhgia`
--

INSERT INTO `danhgia` (`idmonan`, `tentaikhoan`, `danhgia`) VALUES
(2, 'thangyb27', '5.0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaimonan`
--

CREATE TABLE `loaimonan` (
  `id` int(11) NOT NULL,
  `tenloai` varchar(50) NOT NULL,
  `anh` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaimonan`
--

INSERT INTO `loaimonan` (`id`, `tenloai`, `anh`) VALUES
(1, 'Xào', 'uploads/xao.jpg'),
(2, 'Hấp', 'uploads/hap.jpg'),
(3, 'Luộc', 'uploads/luoc.jpg'),
(4, 'Nướng', 'uploads/nuong.jpg'),
(5, 'Quay', 'uploads/quay.jpg'),
(6, 'Chiên', 'uploads/chien.jpg'),
(7, 'Kho', 'uploads/kho.jpg'),
(8, 'Trộn', 'uploads/tron.jpg'),
(9, 'Áp chảo', 'uploads/ap_chao.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `monan`
--

CREATE TABLE `monan` (
  `id` int(11) NOT NULL,
  `tenmonan` varchar(255) NOT NULL,
  `anh` text NOT NULL,
  `video` text NOT NULL,
  `mota` text NOT NULL,
  `cachlam` text NOT NULL,
  `noiban` text NOT NULL,
  `idloaimonan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `monan`
--

INSERT INTO `monan` (`id`, `tenmonan`, `anh`, `video`, `mota`, `cachlam`, `noiban`, `idloaimonan`) VALUES
(1, 'Thịt đùi heo kho tiêu', 'uploads/thit_dui_heo_kho_tieu.jpg', '', 'Thịt heo kho có lẽ là một món kho luôn có mặt hầu hết trong các bữa cơm của mỗi gia đình. Bởi cách làm vô cùng đơn giản mà cho ra một vị ngon đậm đà, cực hấp dẫn nên phần lớn mọi người đều yêu thích món ăn này - thịt đùi heo kho tiêu.', '1. Sơ chế nguyên liệu\nThịt đùi heo mua về đem đi rửa sạch với nước muối. Sau đó cắt miếng vừa ăn và để ráo. Hành tím và tỏi bóc bỏ vỏ rồi lần lượt băm nhuyễn. Hành lá rửa sạch với nước, để ráo, cắt nhỏ.\n\n2. Ướp gia vị thịt\nCho vào tô đựng thịt 1/2 muỗng canh hạt nêm, 1 muỗng canh đường, 1.5 muỗng canh nước mắm, 1/2 muỗng canh bột ngọt, 1/2 muỗng cà phê tiêu xay, 1 muỗng canh nước màu dừa, hành tím và tỏi băm nhuyễn, 2 muỗng cà phê dầu ăn rồi trộn đều và ướp trong 15 phút cho thịt thấm gia vị.\n\n3. Kho thịt\nBắc chảo lên bếp, cho thịt đã ướp vào chảo rồi xào đến khi thịt săn lại và có mùi thơm. Kế tiếp, cho một lượng nước bằng 1/4 chén nước (chén ăn cơm) vào rồi kho cho đến khi nước trong chảo sệt lại. Nêm nếm cho vừa ăn rồi thêm hành lá cắt nhỏ, tiêu vào và tắt bếp.\n\n4.Thành phẩm\nThịt đùi kho tiêu có mùi thơm của hành lá và tiêu. Miếng thịt kho chín mềm, gia vị nêm nếm rất vừa ăn. Cắn một miếng thịt, ăn một muỗng cơm mà ôi chao, thật ngon miệng muốn ăn hoài thôi.', 'Hà Nội', 7),
(2, 'Mướp xào tỏi', 'uploads/muop_xao_toi.jpg', '', 'Mướp xào tỏi món ăn đơn giản, quen thuộc nhưng lại mang đến nhiều hương vị thơm ngon, hấp dẫn', 'Bước 1: Sơ chế nguyên liệu\nMướp sau khi mua về thì gọt vỏ, rửa sạch rồi cắt thành từng miếng vừa ăn. Tỏi lột vỏ, rửa lại với nước cho sạch, rồi đập dập. Bạn cũng có thể băm nhuyễn theo sở thích nha.\n\nBước 2: Xào mướp\nBắc một cái chảo lên bếp, sau đó cho dầu ăn và tỏi vào, phi lên cho tỏi vàng thơm. Tiếp đó cho mướp vào đảo đều cùng với một ít nước, nêm nếm với một ít hạt nêm, đường, bột ngọt và dầu hào. Xào đến khi mướp vừa chín tới thì tắt bếp.', 'Hà Giang', 1),
(3, 'Thịt bò xào cần tây', 'uploads/thit_bo_xao_can_tay.webp', '', 'Thịt bò xào cần tây, kết hợp thêm với vài nhánh tỏi tây, củ hành tây và cà rốt thật sự rất thơm, ăn giòn và giàu dinh dưỡng.', '1.  Sơ chế nguyên liệu\n Đầu tiên, thịt bò mua về bạn đem rửa qua với nước muối loãng, sau đó rửa sạch rồi cắt lát mỏng vừa ăn và cho ra tô.\nTiếp đến, nhặt bỏ lá hư hỏng và vàng úa của rau cần tây, sau đó rửa sạch rồi cắt khúc thành những khúc nhỏ vừa ăn.\n\n2. Làm thịt bò xào cần tây\nBắc chảo lên bếp và cho vào chảo 2 muỗng cà phê dầu ăn. Đợi dầu nóng thì cho thịt bò vào xào nhanh trong vòng 2 phút ở lửa lớn.\nĐến khi thịt săn lại thì cho vào thịt khoảng 4 muỗng canh nước lọc rồi hạ lửa vừa và tiếp tục xào thịt thêm khoảng 3 phút nữa cho rút bớt nước.\nTiếp đến, cho cần tây đã cắt nhỏ vào chảo, nêm vào đó 2 muỗng cà phê hạt nêm, 1/2 muỗng cà phê bột ngọt cùng 1 muỗng cà phê muối.\nĐảo đều hỗn hợp trong khoảng 3 phút để cần tây cùng thịt bò chín và thấm gia vị. Khi món xào đã chín, bạn nêm nếm lại gia vị cho vừa ăn rồi tắt bếp.\n\n3. Thành phẩm\nCho món thịt bò xào cần tây ra dĩa. Để món ăn được hấp hơn, bạn có thể rắc thêm ít tiêu xay lên trên.\nMón ăn trông thật bắt mắt với màu xanh xanh của cần tây. Thịt bò được xào chín vừa nên vẫn giữ được độ ngọt mềm, kết hợp với đó là cần tây vẫn giữ được mùi hương đặc trưng và độ giòn khi ăn. ', 'Hà Nội', 1),
(4, 'Mực ống xào dứa', 'uploads/muc_ong_xao_dua.png', '', 'Từng miếng mực trắng tinh, giòn sần sật thêm những miếng dứa chín vàng ươm mang vị chua ngọt, dậy mùi thơm của tỏi, tiêu, cần tây ăn rất đã miệng.', '+) Nguyên liệu:\r\n\r\n- 300g mực ống\r\n- 100 g dứa\r\n- 2 cây cần tây\r\n- 1 củ hành tây nhỏ\r\n- 8 tép tỏi\r\n- 1½ thìa cà phê hạt nêm\r\n- ½ thìa cà phê tiêu xay\r\n- 1 thìa cà phê nước mắm\r\n\r\n+) Hướng dẫn:\r\n\r\nBước 1: Sơ chế mực\r\n1. Mực tươi mua về rút đầu, tách bỏ nội tạng, xương sống và cả lớp da tím (nếu muốn).\r\n2. Rửa lại mực với nước sạch nhiều lần, để cho ráo nước và thái mực theo hình tùy thích (bông hoa, vảy rồng, ống tròn,…)\r\n3. Tỏi và gừng bỏ vỏ, rửa với nước và đập dập.\r\n4. Đun sôi nồi nước với gừng rồi cho mực vào chần trong 30 giây rồi vớt ra để ráo nước.\r\nBước 2: Sơ chế các nguyên liệu khác\r\n1. Dứa gọt bỏ vỏ, khoét mắt, rửa qua nước sạch rồi thái lát dày cỡ 1 cm.\r\n 2. Cần tây cắt gốc, rửa với nước sạch rồi thái đoạn 3 cm.\r\n 3.Hành tây bóc vỏ, cắt rễ, rửa qua và bổ múi cau.\r\nBước 3: Xào các nguyên liệu\r\n1. Phi thơm ½ phần tỏi cùng chút dầu ăn, xào mực cùng gia vị rồi trút ra một chiếc đĩa sạch.\r\n2. Vẫn sử dụng chiếc chảo nóng đó, phi thơm nốt chỗ tỏi còn lại.\r\n3.Cho lần lượt hành tây, dứa và cần tây vào xào, nêm nếm gia vị cho vừa.\r\n4. Sau khi rau củ chín, cho mực vào đảo cùng khoảng 1 phút trên lửa vừa rồi tắt bếp.\r\nBước 4: Hoàn thành', '', 1),
(5, 'Lòng gà xào mướp hương', 'uploads/long_ga_xao_muop_huong.jpg', '', 'Lòng gà xào mướp một món xào ngon lạ với hương thơm tuy đơn giản mà không kém phần thơm ngon với vị ngọt lịm của mướp hương quyện với vị bùi béo của gan và cái giòn giòn của mề gà.', '+) Nguyên liệu:\n\n- 300g lòng gà\n- 700g mướp\n- 3 nhánh hành lá\n- 5 nhành ngò rí\n- 3 tép tỏi\n- \n- 1 muỗng canh dấm\n- 4 muỗng canh dầu ăn\n- 1 muỗng canh nước mắm\n- Gia vị thông dụng (muối, đường, hạt nêm, bột ngọt,tiêu xay)\n\n+) Cách làm:\n\n1. Sơ chế và ướp lòng gà\nĐể lòng gà sạch nhớt và khử mùi hôi bạn cho gà ngâm với 1 muỗng canh muối và 1 muỗng canh giấm khoảng 10 phút, sau đó rửa sạch lại với vài lần nước.\nTiến hành cắt nhỏ lòng gà thành những miếng vừa ăn rồi cho vào tô.\nƯớp lòng gà với 1/2 muỗng cà phê bột ngọt, 1/2 muỗng cà phê muối, 1/2 muỗng cà phê hạt nêm, 1 muỗng cà phê đường, 1 ít tiêu rồi đảo đều, ướp khoảng 15 phút cho phần lòng gà được thấm vị.\n2. Sơ chế những nguyên liệu khác\nMướp bạn gọt bỏ vỏ, rửa sạch rồi cắt thành những khúc vừa ăn.\nTỏi băm nhỏ. Hành ngò cắt khúc khoảng 2 lóng tay.\n3. Xào lòng gà với mướp\nĐặt chảo lên bếp rồi cho vào 3 muỗng canh dầu ăn, cho 1/2 phần tỏi băm vào phi thơm\nTiếp đến bạn cho lòng gà đã ướp vào xào trên lửa vừa khoảng 10 phút cho lòng gà chín rồi đổ ra đĩa.\nTiếp tục cho vào 1 muỗng canh dầu ăn và phi thơm phần tỏi còn lại. Rồi cho mướp vào xào cùng với 1 muỗng canh nước mắm, 1/2 muỗng cà phê bột ngọt, 1/2 muỗng canh đường và xào khoảng 5 phút cho mướp săn lại.\nCuối cùng cho phần lòng gà đã xào trước đó vào cùng với mướp đảo đều thêm 5 phút, nêm nếm lại gia vị sao cho vừa ăn, rắc hành ngò, 1 ít tiêu vào và tắt bếp.\n4. Hoàn thành', '', 1),
(6, 'Gà hấp muối sả', 'uploads/ga_hap_muoi_xa.jpg', '', 'Vào những ngày hè nóng bức như hiện tại, nói đến chế biến gà thành món ăn, chắc chắn người đầu bếp nghĩ ngay tới món gà hấp muối sả. Món hấp ngon này cực kì hợp để tụ tập ăn cùng bạn bè và cũng rất tuyệt vời để tiếp khách. Món gà hấp muối sả này có vị thanh mát của thịt gà nhưng lại rất thơm từ sả và các loại gia vị khác. Món gà hấp muối sả để nhậu rượu cũng ngon hoặc ăn với cơm thì cũng cực kỳ tuyệt vời đó nha.', '+) Nguyên liệu:\n\n- 1 con gà\n- 1 nhánh gừng\n- 1 trái ớt\n- 8 nhánh sả\n- 2 củ hành tím\n- 3 tép tỏi\n- 10 lá chanh\n- 1/2 trái chanh\n- 1 muỗng cafe bột nghệ\n- 1 muỗng canh bột tỏi\n- 1 muỗng canh nước mắm\n- 500gr muối hột\n- Gia vị thông dụng khác (muối, đường, hạt nêm, tiêu xay)\n\n+) Cách chế biến:\n1. Sơ chế nguyên liệu:\nGà nguyên con đã được làm sạch và lấy nội tạng ra, bạn rửa qua nước muối pha loãng, sau đó rửa sạch lại với nước, rồi để ráo.\nBạn mang sả đi rửa sạch, đập dập rồi cắt khúc. Gừng bạn cạo bỏ vỏ, rồi cắt lát.\nLá chanh bạn rửa sạch, lấy 1 lá cắt nhỏ. Ớt bạn rửa sạch, bỏ cuống. Hành tím, tỏi bạn lột bỏ vỏ.\n2. Ướp gà\nBạn trộn đều hỗn hợp gồm: 1 muỗng cà phê bột nghệ, 1 muỗng canh bột tỏi, 1 muỗng canh đường, 1 muỗng canh hạt nêm, 1 muỗng canh nước mắm, 1 muỗng canh muối rồi xoa khắp con gà.\nBạn nhét 1/2 phần gừng, hành tím, tỏi, 3 nhánh sả đập dập vào trong bụng gà.\n3. Hấp gà\nBạn chuẩn bị 1 cái nồi to, lót giấy bạc, trải 500gr muối hột, 10 lá chanh, 5 nhánh sả, phần gừng còn lại vào, đặt con gà lên trên rồi cuộn giấy bạc lại.\nĐặt nồi lên bếp hấp trên lửa nhỏ khoảng 45 phút cho gà chín đều.\nBạn pha nước chấm với công thức: cho vào chén 1 trái ớt, 1 muỗng canh muối, 1 muỗng canh đường, bạn giã nhuyễn rồi thêm 1 muỗng canh sữa đặc, 1 muỗng cà phê tiêu, vắt lấy cốt 1/2 quả chanh, trộn đều hỗn hợp.\nSau đó cho vào 1 lá chanh cắt nhỏ, nêm nếm cho vừa ăn là hoàn thành rồi!\n4. Hoàn thành', '', 2),
(7, 'Tôm hấp nước dừa', 'uploads/tom_hap_nuoc_dua.jpg', '', 'Tôm hấp nước dừa luôn nằm trong danh sách những món hấp ngon mà bạn phải biết. Tôm là một loại thực phẩm giàu chất canxi tốt cho cơ thể, nhất là hệ xương, đặc biệt nó có thể phòng chống bệnh loãng xương cho người ở độ tuổi trung niên và cao tuổi. Nước dừa mát và bổ có tác dụng thanh nhiệt, đẹp da, giữ dáng… Khi kết hợp 2 nguyên liệu này lại với nhau, đồng thời chế biến bằng cách hấp cực đơn giản, thanh đạm. Với món ăn này chắc chắn sẽ làm đổ gục tất cả những người mê ẩm thực đó nha.', '+) Nguyên liệu:\n\n- Tôm 500gr (mua loại 100gr được 4-5 con)\n- 1 quả dừa xiêm\n- 1 củ hành tím\n- Xà lách, dưa leo, cà chua, hành lá để trang trí\n+) Cách làm tôm sú hấp nước dừa:\nBước 1:\nTôm đem rửa sach cắt bớt râu, để ráo.\nBước 2:\nDừa xiêm gọt vỏ, tỉa khoanh tròn, chắt lấy nước. Nhớ là gọt sao cho đẹp để còn xếp tôm cho bắt mắt nhé.\nBước 3:\nĐổ nước dừa vào nồi, đập dập củ hành tím cho vào nồi nước dừa nấu cho sôi rồi nêm muối bột nêm cho nước vừa ăn, cho tôm sú đã rửa sạch vào, đảo đều.\nSau khi thấy vỏ tôm đỏ ta tắt bếp rồi vớt.\nBước 4:\nTrang trí Xếp tôm quanh miệng quả dừa. Đun lại nước dừa đã lấy nước khi này đổ vào trái dừa đã xếp tôm là xong nhé.Trang trí thêm cà chua tỉa hoa, dựa chuột và hành lá.', '', 2),
(8, 'Cá hấp hành gừng', 'uploads/ca_hap_hanh_gung.jpg', '', 'Nói đến cá hấp người ta hay hình dung đến món cá hấp bia, nhưng có phải món ăn đó đã quá nhàm chán đối với bạn rồi phải không. Vì vậy hôm nay chúng ta sẽ đổi vị với món cá hấp với hành gừng. Vị thơm, cay của gừng ớt khiến món ăn không còn vị tanh nữa. Mùi thơm của nó cùng độ ngọt của cá khiến cho chúng ta ăn mãi không chán. Cùng nhậu cá hấp gừng với vài ly bia thì quả là tuyệt vời đấy nhỉ.', '+) Nguyên liệu:\n\n1 con cá bất kì (có thể là cá diêu hồng, cá rô phi, cá mè hay cá chim đều được)\nMuối và tiêu để nêm nếm\n1 củ gừng nhỏ\n1/4 chén nước tương\n1 muỗng canh rượu gạo\nHành lá\nNửa chén dầu ăn\n+) Cách làm:\n\nCá phải được moi ruột, đánh vẩy và làm sạch nhưng giữ đầu và đuôi nguyên vẹn. Khi mang về, người ta thường rửa cá với nước muối cho bớt tanh, một mẹo nhỏ là bạn rửa cá với nước đường và chanh cũng sẽ không nghe mùi cá tanh nữa. Cá sơ chế xong để cho ráo trước khi chế biến.\nĐặt cá lên đĩa, khứa cá rồi ướp với muối và tiêu, chờ khoảng 15, 20 phút để thấm gia vị. Thái sợi củ gừng và xắt hành lá, một phần thì nhét vào bụng cá, một phần thì đặt lên trên cá. Đổ nước vào nồi hấp rồi đặt đĩa cá lên xửng hấp, hấp cách thủy trong 8 phút hoặc đến khi dùng dao đâm thử vào thịt cá thì thấy thịt cá bong ra dễ dàng.\nTrong khi đang hấp cá, bạn pha nước tương, rượu gạo và một muỗng canh nước trong chén để rưới lên cá khi chín.\nSau khi hấp chín cá, cẩn thận gắp đĩa cá ra khỏi nồi hấp, đổ bỏ nước trong đĩa cá. Rắc thêm một ít ngò, hành lá lên trên để trang trí. Bắc cái chảo nhỏ lên bếp gas, đun nửa chén dầu cho đến khi sôi lên nhưng chưa bốc khói, đổ trực tiếp dầu ăn lên phần hành lá và ngò. Rưới hỗn hợp nước tương đã pha lên cá là có thể dùng ngay.', '', 2),
(9, 'Ngao hấp xả', 'uploads/ngao_hap_xa.jpg', '', 'Ngao hấp sả là món hải sản được chế biến bằng cách hấp khá phổ biến và được nhiều người yêu thích. Hấp là phương pháp chế biến cho món ăn này rất đơn giản, tuy nhiên vẫn giữ được độ ngọt, dai, hương vị tươi mát và giá trị dinh dưỡng đến từ thịt nghêu. Ngao hấp sả (nghêu hấp sả) là món ăn dân dã, quen thuộc với vị ngọt đặc trưng của nghêu tươi kết hợp với vị cay cay của ớt, thơm thơm của sả làm nên một món ăn tuyệt vời.Nước ngao thì ngọt đằm, thơm mát vì vậy món ăn này rất thích hợp vào những hôm trời nắng nóng mà cần bát canh mát ăn để giải nhiệt thì cực kì là hợp lý.', '+)Bước 1:huẩn bị nguyên liệu:\n\nĐầu tiên, ngao khi bạn mua về bạn cần phải làm sạch những chất bẩn cũng như cát đất mà nó tích tụ trong quá trình sinh sống dưới nước. Bạn nên ngâm ngao trong nước ấm pha với muối, cắt thêm vài lát ớt trong vòng 1 giờ để ngao nhả hết cát đất ra. Đúng 1 giờ sau, bạn vớt nghêu ra và đem rửa lại dưới vòi nước thật sạch rồi để ráo.\nVới sả thì bạn cũng rửa sạch cát đất để đảm bảo an toàn vệ sinh cho món ăn. 2 cây sả bạn đem thái lát mỏng, còn 3 cây sả còn lại thì đập dập rồi cắt khúc ra. Ớt bạn cho vào trong cối rồi giã nhỏ ra.\nBước 2: Pha nước mắm chấm\nĐể món ngao hấp thêm thơm ngon, bạn cần phải làm thêm nước chấm cực ngon nữa. Cách làm nước chấm cũng cực kỳ đơn giản.\nĐầu tiên, bạn cho 1 muỗng canh nước mắm vào chén, sau đó cho thêm 1 muỗng canh đường và 1 muỗng canh nước ấm nóng.\nTiếp đến, bạn dùng thìa khuấy đều cho đường tan hết để món nước chấm thêm thơm ngon, hoà quyện các nguyên liệu. Cho thêm vào nước chấm vài giọt nước cốt chanh để phần nước mắm chấm đủ vị chua ngọt.\nSau cùng thì bạn cho thêm ớt vào để món nước chấm thêm vị cay kích thích cùng với màu sắc ngon mắt.\nBước 3: Hấp ngao (nghêu)\nĐầu tiên, cho vào nồi hấp 250ml nước. Tiếp đến, bạn cho sả đã thái lát và cắt khúc vào trong nồi, sau đó cho thêm ớt vào nồi. Bạn cho thêm vào 1 thìa cà phê bột nêm, 1 thìa cà phê đường vào nước hấp ngao để ngao hấp đậm vị hơn.\nĐun cho tới khi nồi nước sôi lên thì cho ngao vào nồi và đảo đều tay. Khi thấy ngao đã mở vỏ thì lấy nắp đậy kín lại. Canh khi ngao đã mở hoàn toàn lớp vỏ thì tắt bếp.\nLưu ý không nên hấp ngao quá lâu vì ngao sẽ lần lượt chảy hết nước ra và khô lại, làm món ngao hấp bị dai và không còn ngon.', '', 2),
(10, 'Thịt bắp bò luộc gừng sả', 'uploads/thit_bap_bo_luoc_hanh_xa.jpg', '', 'Từng miếng thịt bò thấm, thơm mùi gừng, sả và gia vị được cuốn với bún, rau xà lách, chấm kèm với nước mắm chua ngọt.', '+) Nguyên liệu:\n\n– 700g thịt bắp bò\n– 4-5 cây sả\n– 1 lóng gừng\n– 1 thìa canh sả băm\n– Muối, hạt nêm, xì dầu, đường, tỏi, hạt nêm\n– Bún, rau xà lách xoăn, rau thơm\n– Bánh đa.\n Cách làm:\n\n1. SƠ CHẾ:\n– Thịt bắp bò rửa sạch, để ráo, nếu miếng bắp bò lớn thì xẻ làm đôi.\n– Tỏi bóc vỏ, băm nhuyễn.\n– Trộn đều tỏi, sả băm, hai thìa nhỏ muối, một thìa nhỏ hạt nêm, dùng tay thoa đều hỗn hợp tỏi, sả vào miếng thịt bò.\n– Dùng sợi dây lạt hay chỉ cột chặt miếng thịt, ướp qua đêm hoặc khoảng 5-6 tiếng.\n– Cây sả rửa sạch, cắt khúc ngắn, dùng cán dao đập dập.\n– Rau xà lách xoăn, rau thơm, rửa sạch, để ráo.\n2. THỰC HIỆN:\n– Cho gừng, sả vào nồi nước, đun sôi.\n– Cho tiếp miếng thịt bò ở vào đun cùng, thỉnh thoảng hớt bỏ bọt, thêm vào nồi sả một thìa canh xì dầu, một thìa nhỏ đường, một thìa nhỏ muối để phần gia vị thấm vào miếng thịt.\n– Đun đến khi xiên đầu đũa qua miếng thịt mềm như ý thì tắt bếp, để nguội, gắp ra, thái thịt bò thành lát vừa ăn.\n3. CÁCH DÙNG:\nKhi dùng thì xếp thịt bò ra đĩa, dùng kèm với rau xà lách, bún, cuốn với bánh đa, chấm với nước mắm chua ngọt. Bạn có thể cuốn thêm dưa leo, dứa, cắt mỏng.', '', 3),
(11, 'Tràng lợn luộc trộn măng tươi', 'uploads/trang_lon_luoc_mang_tuoi.jpg', '', 'Tràng lợn giòn sần sật trộn với măng tươi thái sợi, lạc rang và rau thơm thái nhỏ sẽ là món nhắm ngon cho ông xã hoặc ăn cùng cơm cũng thú vị.', '+) Nguyên liệu:\n\n300 gram tràng lợn\n150 gram măng tươi\n1 củ cà rốt\nRau thơm: húng quế\n1 nhánh gừng nhỏ\nLạc rang chín, giã thô (không giã  nát)\nGia vị Muối, giấm, đường, nước mắm, tỏi, ớt quả\n+) Cách làm:\n\nBước 1: Những hôm không có nhìu thời gian, mình thường hay mua tràng lợn đã luộc sẵn chỗ người quen thì công đoạn sơ chế sẽ giảm đi được 1 phần. Nếu là tràng lợn sống mua ở ngoài chợ, các nàng mua khoảng 300 gram. Tràng mua về rửa sạch rồi để trong rổ cho ráo nước. Sau đó, trộn cùng tràng 1 thìa bột mỳ hay 1 thìa canh nước mắm. Bóp đều và để khoảng 15 phút rồi đem rửa sạch (mục đích cho bột mỳ hoặc nước mắm là để giảm bớt mùi hôi của tràng lợn nhé\nBước 2: Cho tràng lợn vào nồi, thêm vào đó gừng giã mịn, đổ nước lạnh sao cho ngập tràng (nước ngập mới giúp tràng khi luộc không bị khô và thâm lợi, với bất cứ món luộc nào các nàng cũng nên đổ nước ngập nguyên liệu nhé), sau đó đun sôi, luộc chín, để nguội và đem cắt nhỏ\nBước 3: Măng tươi đem rửa sạch, thái sợi. Cho nước vào nồi, đun sôi, nước sôi các nàng trút măng đã thái sợi vào luộc đến khi thấy măng mềm, lấy ra sả lại dưới nước lạnh cho bớt mùi chua, để ráo nước\nBước 4: Cà rốt đem rửa sạch, nạo sợi dài nhỏ rùi cho vào nước muối pha loãng ngâm khoảng 15 phút. Sau đó, các nàng vớt ra và rửa lại bằng nước đun sôi để nguội, vớt ra vắt vắt nhẹ cho cà rốt được ráo nước và để ra rổ cho cà rốt được ráo hoàn toàn.\nBước 5: Các nguyên liệu đã sẵn sàng rùi. Bây giờ, chúng mình chỉ việc pha nước mắm trộn nộm gồm: 3 thìa con mắm ngon hòa tan với 1 thìa canh đường, 1 thìa giấm rùi thêm tỏi ớt giã nhuyễn vào. Tiếp đó, cho các nguyên liệu: tràng, măng, cà rốt trộn đều với nhau. Từ từ đổ nước mắm trộn nộm vào, trước tiên là đổ 1/2 số nước mắm, trộn đều rùi nếm thử. Nếu thấy nhạt, các nàng từ từ cho thêm mắm cho đến khi vừa miệng nhé. Các nàng có thể cho thêm nước cốt chanh tươi nếu thích nhé. Cuối cùng, các nàng chỉ việc thêm lạc rang giã sơ và rau húng quế thái nhỏ vào và múc ra đĩa là món ăn đã hoàn thành rùi', '', 3),
(12, 'Bún thịt luộc chấm mắm nêm', 'uploads/bun_thit_luoc_cham_mam_nem.jpg', '', 'Thỉnh thoảng bạn đổi món bún chan quen thuộc bằng bún trộn kiểu đơn giản mà vẫn ngon miệng này cho cả nhà nhé.', '+) Nguyên liệu:\n\n300g thịt ba chỉ\n2 lát dứa\nNửa chai mắm nêm (300ml)\nĐường, ớt, chanh, tỏi, hạt nêm, giấm\n1 củ cà rốt\nRau xà lách, diếp cá, rau răm, húng (bạn có thể thêm các loại rau thơm khác tùy theo sở thích)\nLạc rang vàng, giã nhuyễn\nBún\n+) Cách làm:\n\n– Luộc thịt, thêm vào nồi luộc thịt khoảng nửa thìa nhỏ muối, chút xíu hạt nêm.\n– Đun tầm 8 phút, tùy theo miếng thịt dày hay mỏng. Tắt bếp, đậy kín nắp nồi. Thịt sẽ tiếp tục chín, không nên đun thịt lâu, sẽ làm thịt không ngọt và da không dai.\n– Thịt chín lấy ra thái lát vừa ăn, xếp lên đĩa.\n– Băm nhuyễn dứa.\n– Giã nhuyễn tỏi, ớt và ba thìa canh đường, trộn dứa và tỏi ớt vào bát mắm nêm. Dùng thìa trộn đều, nêm nếm lại tùy theo khẩu vị của bạn, vắt vào bát mắm nêm vài giọt chanh.\n– Rau xà lách, rau diếp cá, rau răm, húng bỏ bớt cọng già, rửa sạch, để lên rổ cho ráo nước.\n– Cà rốt bào sợi, ngâm vào bát cà rốt ít nước lọc, ba thìa canh giấm, hai thìa nhỏ muối và hai thìa nhỏ đường.\n– Khi ăn bạn thái rau nhỏ để phía dưới bát, thêm bún, xếp vài lát thịt, bên trên chan nước mắm nêm, cà rốt ngâm giấm đường và rắc ít lạc đã rang, trộn đều lên.', '', 3),
(13, 'Lòng lợn luộc trộn hành tây', 'uploads/long_lon_luoc_tron_hanh_tay.jpg', '', 'Món ăn quen thuộc dễ làm có thêm chút hương vị của hành tây sẽ tạo nên chút cảm giác lạ.', '+) Nguyên liệu:\n\n- 400g lòng heo\n- 1 củ hành tây\n- Ớt cay, rau răm, hành lá, tỏi\n- 1 muỗng đường\n- 2 muỗng nước mắm\n- 2 muỗng nước cốt chanh\n- ½ muỗng cà phê tiêu\n+) Cách làm:\n\n- Lòng heo bóp cùng chanh và muối sau đó rửa sạch lại bằng nước. Cho lòng vào nồi luộc cùng 1 muỗng giấm. Sau khi lòng được luộc chín bạn vớt ra tô nước đá để lòng được trắng và giòn. Thái lòng thành miếng vừa ăn.\n- Hành tây thái mỏng, ngâm vào tô nước đá để hành bớt hăng, vớt ra để ráo.\n- Pha hỗn hợp gồm 1 muỗng đường; 2 muỗng nước mắm; 2 muỗng nước cốt chanh;  ½ muỗng cà phê tiêu, 1 muỗng tỏi ớt, đánh tan.\n- Cho lòng heo vào tô cùng hành tây, đổ hỗn hợp ở trên vào tô, xóc đều, cuối cùng bạn cho rau răm vào.', '', 3),
(14, 'Tôm nướng muối ớt', 'uploads/tom_nuong_muoi_ot.jpg', '', 'Một trong các món nướng dễ làm tại nhà nhất chính là tôm nướng muối ớt. Món ăn này có phần nguyên liệu chuẩn bị khá đơn giản và dễ kiếm tại các chợ, siêu thị gần nhà. Tôm nướng muối ớt khi được ướp kỹ sẽ có vị hơi mặn của muối, cay của ớt và ngọt thịt từ tôm. Còn gì tuyệt hơn khi vào ngày lạnh được quây quần cùng bạn bè và gia đình, thưởng thức những con tôm nướng cay cay nóng hổi đúng không?', '+) Nguyên liệu:\n\n500g tôm\n3 trái ớt\n9 tép tỏi\n1 muỗng canh muối hạt\n1 muỗng canh dầu hào\n1 muỗng canh mật ong\n1 muỗng cà phê sa tế\n2 trái tắc\nGia vị thông dụng (tiêu, hạt nêm, bột ngọt, muối)', '', 4),
(15, 'Thịt ba chỉ nướng BBQ', 'uploads/thit_ba_chi_nuong_bbq.jpg', '', 'Thịt ba chỉ nướng là một trong các món nướng đơn giản và hầu như luôn xuất hiện trong bất kỳ menu tiệc nướng BBQ nào. Một phần bời vì thịt lợn là thực phẩm quen thuộc với rất nhiều người, hơn nữa phần thịt ba chỉ do được xen lẫn cả nạc và mỡ nên khi nướng lên có vị ngậy rất ngon. Do đó khi tìm hiểu cách làm BBQ tại nhà nhiều người không thể bỏ qua món ngon này.', '+) Nguyên liệu:\n\n- Thịt ba chỉ: 500 gram\n- Tiêu xay: 1/2 muỗng cà phê\n- Hành tím, tỏi\n- Gia vị: Dầu hào, nước tương, nước mắm, hạt tiêu, đường trắng, dầu ăn, tương ớt\n+) Cách làm:\n\nB1) Sơ chế các nguyên liệu \n- Rửa sạch thịt ba chỉ với nước, ngâm trong nước muối loãng rồi rửa sạch lại với nước rồi đem thái thành những miếng thịt vừa phải (bạn nên nướng thịt cả miếng to sẽ mang lại hương vị ngon hơn, không bị khô)\n- Hành tím, tỏi bỏ vỏ, đập dập rồi băm nguyễn. \nB2) Ướp thịt trước khi nướng\n- Cho 1 muỗng canh nước mắm, 2 thìa cà phê đường, 2  muỗng cà phê dầu hào cùng với 2 muỗng cà phê tương ớt, 1/2 muỗng cà phê muối, 1/2  muỗng cà phê tiêu và 1 muỗng canh dầu ăn vào bát trộn đều.\n- Sau đó, cho thêm tỏi băm, hành cắt nhỏ và cho thịt vào bát trộn đều các nguyên liệu rồi dùng màng bọc thực phẩm bọc lại để vào ngăn mát tủ lạnh khoảng 30 phút cho thịt ngấm đều các gia vị.\nB3) Tiến hành nướng thịt trên bếp than hoa\n- Nhóm bếp nướng than hoa, sử dụng bếp nướng than hoa giúp mang lại hương vị thơm ngon hơn cho thịt ba chỉ, hấp dẫn chúng ta hơn bao giờ hết.\n- Nướng thịt trên bếp than, lật thịt đều tay để thịt vàng bên ngoài và chín đều bên trong.', '', 4),
(16, 'Cánh gà nướng mật ong', 'uploads/canh_ga_nuong.jpg', '', 'Đã nhắc đến những món nướng ngon thì làm sao bỏ qua được cánh gà nướng đúng không? Món ăn vừa quen thuộc vừa dễ chế biến này sẽ cực kỳ thích hợp với những bữa tiệc có trẻ con đó. Lũ trẻ sẽ tranh nhau các phần cánh gà nướng và bạn có thể có thêm thời gian để chuẩn bị các món nướng BBQ khác.', '+) Nguyên liệu:\n\n1kg cánh gà\nHành tím xay nhuyễn\nTỏi băm\nGia vị: Muối tỏi, bột gừng, hạt nêm, dầu hạt điều, đường, mật ong, dầu oliu\n\n+) Cách làm:\n\nB1: Sơ chế cánh gà\nCánh gà sau khi mua về bạn rửa nhiều lần qua nước lạnh. Sau đó, bạn hòa 2 muỗng canh muối vào 2 lít nước nóng tầm 70 độ C rồi ngâm cánh gà trong vòng 5 phút.\nTrong lúc ngâm, bạn dùng dao cạo sạch váng mỡ, cặn bẩn bám trên cánh gà. Hết 5 phút, bạn vớt ra và rửa cánh gà lại với nước sạch, để ráo.\nB2: Ướp cánh gà\nGà sau khi được sơ chế sạch sẽ, bạn đem đi ướp với 1 muỗng cà phê hành tím xay nhuyễn, 1 muỗng cà phê tỏi băm, ½ muỗng cà phê bột gừng, ½ muỗng cà phê muối tỏi, 1 muỗng cà phê hạt nêm, 1 muỗng cà phê đường và 1 muỗng canh dầu hạt điều để cánh gà có màu đẹp mắt nhé!\nSau đó, bạn đeo bao tay vào để trộn đều gà với gia vị.\nB3: Nướng cánh gà lần 1\nƯớp cánh gà xong, bạn lót 1 tờ giấy thấm dầu rồi xếp toàn bộ cánh gà vào nồi chiên không dầu. Sau đó, bạn nướng cánh gà ở 180 độ C trong 15 phút.\nB4:Làm sốt mật ong\nTrong lúc chờ nướng cánh gà, bạn tranh thủ làm sốt mật ong để chuẩn bị nướng cánh gà lần 2 nhé!\nBạn cho vào chén 2 muỗng canh mật ong và 1 muỗng canh dầu oliu, sau đó trộn đều lên để hỗn hợp được hòa quyện.\nB5: Nướng cánh gà lần 2:\nCánh gà sau khi nướng trong nồi chiên không dầu được 15 phút, bạn lấy phần cánh gà ra rồi nhúng lần lượt vào sốt mật ong sao cho toàn bộ cánh gà được thấm đẫm sốt.\nSau đó, bạn tiếp tục nướng cánh gà với 180 độ C trong vòng 5 phút.\nB6: Hoàn thành', '', 4),
(17, 'Thịt bò xiên nướng rau củ', 'uploads/thit_bo_xien_nuong_rau_cu.jpg', '', 'Không ngoa nếu nói bò lúc lắc xiên que kèm rau củ là món ăn gần như xuất hiện trong mọi thực đơn đồ nướng của các bữa tiệc BBQ. Món ăn này là sự kết hợp của thịt bò được thái hình quân cờ và các loại rau củ như cà chua bi, ớt xanh, ớt đỏ, hành tây. Do đó khi ăn sẽ không bị ngấy mà đã có những phần rau củ đi kèm. Món nướng xiên que nhiều màu sắc này còn cực kỳ thích hợp khi bạn chụp ảnh sống ảo đó.', '+ Nguyên liệu:\n\n 1kg thăn bò\n 3 quả ớt chuông\n1 củ hành tây\n 1 muỗng canh ngũ vị hương\n2 muỗng canh nước tương\n2 muỗng canh bột tỏi\n1 muỗng canh dầu hào\n2 muỗng canh dầu ô liu\nGia vị thông dụng (muỗi, hạt nêm, tiêu xay)\n\n+) Cách làm:\n\n1. Sơ chế nguyên liệu\n3 quả ớt chuông bạn rửa sạch, bỏ hết hạt bên trong ruột và cắt thành từng miếng vuông vừa ăn khoảng 1.5 lóng tay.\nHành tây bạn lột bỏ vỏ, cắt đôi rồi cắt miếng vuông vừa ăn.\n2.Sơ chế thịt bò\nThịt bò mua về ngâm trong thau nước muối loãng 2 - 3 phút sau đó rửa sạch lại với nước và để ráo.\nTiếp đó để thịt không bị dai, bạn cắt ngang thớ dọc của thịt thành những khối vuông vừa ăn khoảng 1.5 lóng tay.\n3. Ướp thịt bò\nThịt bò bạn cho vào tô và ướp với 1 muỗng canh ngũ vị hương, 2 muỗng canh hạt nêm, 2 muỗng canh nước tương, 2 muỗng canh dầu ô liu, 2 muỗng canh bột tỏi, 1 muỗng canh dầu hào, 1 muỗng canh tiêu xay, trộn đều và ướp trong khoảng 30 phút để thịt thấm gia vị.\n4. Xiên thịt\nThịt sau khi đã ướp thì lấy ra xiên, khi xiên xen kẽ thịt với hành tây, ớt chuông, cứ 1 miếng thịt thì 1 miếng hành tây và 1 miếng ớt chuông.\n5. Nướng thịt\nBạn làm nóng lò nướng trước khoảng 10 phút ở nhiệt độ 180 độ C rồi cho xiên thịt vào nướng trong khoảng 20 phút ở 180 độ C là hoàn thành.', '', 4),
(18, 'Vịt quay', 'uploads/vit_quay.png', '', 'Vịt quay chảo là một món ăn vô cùng lý tưởng khi bạn muốn thay đổi khẩu vị cho gia đình. Là một món chiên thơm ngon, hấp dẫn và lạ miệng với cách làm cực kỳ đơn giản.', 'Đang cập nhật', '', 5),
(19, 'Heo quay da giòn', 'uploads/heo_quay_da_gion.jpg', '', 'Bạn muốn làm một món thật ngon và độc đáo để đãi gia đình vào cuối tuần? Hãy thử ngay công thức làm thịt heo quay tuyệt ngon sau đây cùng ReviewFood nhé! Chỉ với những bước làm đơn giản, dễ dàng là bạn đã có thể trở thành \"master chef\" của cả nhà rồi.', '+) Nguyên liệu:\n\n1 miếng thịt ba chỉ khoảng 1kg\n1 thìa muối\n1 thìa cà phê tiêu\n1 thìa cà phê bột ngũ vị hương\n1 thìa cà phê bột tỏi\n1 thìa đường\n1 thìa nước tương\n1/2 thìa tương ăn phở\n1 thìa rượu mai quế lộ\n1 thìa giấm\n\n+) Cách làm:\n\nBước 1: Chọn mua thịt\n- Thịt ba chỉ nên chọn mua nguyên miếng to để khi đặt miếng thịt trên khay được bằng phẳng, khi nướng da heo sẽ nổ đều hơn. Miếng thịt ba chỉ nên chọn miếng không quá nạc cũng không quá nhiều mỡ sẽ ngon hơn. Thịt còn tươi mới và có màu hồng tươi, thớ thịt săn, da mỏng. Lớp mỡ có màu sáng bóng, có độ rắn, miếng thịt phải được dính liền không bị long nhé.\nBước 2: Rửa thịt\n- Thịt ba chỉ đem cạo sạch phần bì rồi rửa lại thật sạch sau đó dùng giấy thấm khô nước. Đối với thịt heo được giữ trong ngăn đông tủ lạnh thì cần được rã đông hoàn toàn để chế biến.\nBước 3: Khứa thịt\n- Dùng dao khứa 5-7 đường theo chiều dọc và chiều ngang miếng thịt, như vậy khi ướp thịt sẽ thấm gia vị nhanh hơn.\nBước 4: Pha hỗn hợp ướp thịt\n- Bạn cho muối, đường, tiêu, bột ngũ vị hương đã chuẩn bị ở trên vào 1 chiếc bát con và trộn đều, như vậy chúng ta được phần ướp bột khô.\n- Nước tương, nước tương ăn phở, rượu mai quế lộ cho vào 1 chiếc bát con khác và khuấy đều cho hòa tan, chúng ta được hỗn hợp nước ướp thịt.\nBước 5: Ướp thịt\n- Đặt miếng thịt trên thớt và để phần thịt ngửa lên trên, phần da ở dưới. Rắc đều hỗn hợp bột khô lên khắp miếng thịt và xoa đều sau đó mới rưới hỗn hợp nước ướp lên khắp miếng thịt, tiếp tục xoa đều khắp miếng thịt. Lưu ý: không xoa gia vị lên phần da heo, chỉ ướp phần thịt thôi nhé. Ướp thịt khoảng 30 phút đến 1 tiếng là có thể đem đi nướng.\nBước 6: Làm chín miếng thịt \nBạn đặt miếng thịt vào đĩa, phần da lên trên sau đó cho vào lò vi sóng bắn 4 phút. Mở lò vi sóng xem phần da chín 1 nửa thì đóng cửa lò vi sóng và bắn tiếp 4 phút nữa là được. Nếu không có lò vi sóng bạn có thể đem luộc cho miếng thịt vừa chín là vớt ra ngay, sau đó mới đem ướp gia vị nhé.\nBước 7: Xăm da heo\n- Miếng thịt sau khi đã làm chín phần da thì lấy ra và dùng 1 bó cây xiên thịt để xăm, bạn cứ xăm thật nhiều lần lên phần da, như vậy khi nướng da heo sẽ nổ giòn rụm mà không bị giòn cứng.\nBước 8: Pha hỗn hợp phết lên da heo\nCách Làm Thịt Heo Quay Giòn Rụm, Ngon Như Ngoài Hàng\r\n9 tháng trước - Huyen.NguyenDieu\r\n\r\nfb-icon\r\nBạn muốn làm một món thật ngon và độc đáo để đãi gia đình vào cuối tuần? Hãy thử ngay công thức làm thịt heo quay tuyệt ngon sau đây cùng Nguyễn Kim nhé! Chỉ với những bước làm đơn giản, dễ dàng là bạn đã có thể trở thành \"master chef\" của cả nhà rồi. Vào bếp cùng Nguyễn Kim để trải nghiệm thêm nhiều điều thú vị nhé!\r\nXem nhanh\r\n\r\nCách làm món heo quay da giòn bằng nồi chiên không dầuNguyên liệu làm món thịt quay da giòn bằng nồi chiên không dầu\r\n1 miếng thịt ba chỉ khoảng 1kg\r\n1 thìa muối\r\n1 thìa cà phê tiêu\r\n1 thìa cà phê bột ngũ vị hương\r\n1 thìa cà phê bột tỏi\r\n1 thìa đường\r\n1 thìa nước tương\r\n1/2 thìa tương ăn phở\r\n1 thìa rượu mai quế lộ\r\n1 thìa giấm\r\nCác dụng cụ nhà bếp được sử dụng:\r\nBộ dao chặt heo quay\r\nThớt tre tròn\r\nNồi chiên không dầu\r\nDao thái thịt\r\nChuẩn bị đầy đủ các nguyên liệu để làm heo quay \r\n\r\nCác bước làm món heo quay da giòn bằng nồi chiên không dầu\r\nBước 1: Chọn mua thịt\r\n\r\n- Thịt ba chỉ nên chọn mua nguyên miếng to để khi đặt miếng thịt trên khay được bằng phẳng, khi nướng da heo sẽ nổ đều hơn. Miếng thịt ba chỉ nên chọn miếng không quá nạc cũng không quá nhiều mỡ sẽ ngon hơn. Thịt còn tươi mới và có màu hồng tươi, thớ thịt săn, da mỏng. Lớp mỡ có màu sáng bóng, có độ rắn, miếng thịt phải được dính liền không bị long nhé.\r\n\r\nBước 2: Rửa thịt\r\n\r\n- Thịt ba chỉ đem cạo sạch phần bì rồi rửa lại thật sạch sau đó dùng giấy thấm khô nước. Đối với thịt heo được giữ trong ngăn đông tủ lạnh thì cần được rã đông hoàn toàn để chế biến.\r\n\r\nLàm sạch thịt rồi dùng giấy thấm khô nước\r\n\r\nBước 3: Khứa thịt\r\n\r\n- Dùng dao khứa 5-7 đường theo chiều dọc và chiều ngang miếng thịt, như vậy khi ướp thịt sẽ thấm gia vị nhanh hơn.\r\n\r\nBước 4: Pha hỗn hợp ướp thịt\r\n\r\n- Bạn cho muối, đường, tiêu, bột ngũ vị hương đã chuẩn bị ở trên vào 1 chiếc bát con và trộn đều, như vậy chúng ta được phần ướp bột khô.\r\n\r\n- Nước tương, nước tương ăn phở, rượu mai quế lộ cho vào 1 chiếc bát con khác và khuấy đều cho hòa tan, chúng ta được hỗn hợp nước ướp thịt.\r\n\r\nPha các gia vị với nhau để ướp thịt heo quay\r\n\r\nBước 5: Ướp thịt\r\n\r\n- Đặt miếng thịt trên thớt và để phần thịt ngửa lên trên, phần da ở dưới. Rắc đều hỗn hợp bột khô lên khắp miếng thịt và xoa đều sau đó mới rưới hỗn hợp nước ướp lên khắp miếng thịt, tiếp tục xoa đều khắp miếng thịt. Lưu ý: không xoa gia vị lên phần da heo, chỉ ướp phần thịt thôi nhé. Ướp thịt khoảng 30 phút đến 1 tiếng là có thể đem đi nướng.\r\n\r\nƯớp thịt heo để quay khoảng 30 phút đến 1 tiếng\r\n\r\nBước 6: Làm chín miếng thịt \r\n\r\nBạn đặt miếng thịt vào đĩa, phần da lên trên sau đó cho vào lò vi sóng bắn 4 phút. Mở lò vi sóng xem phần da chín 1 nửa thì đóng cửa lò vi sóng và bắn tiếp 4 phút nữa là được. Nếu không có lò vi sóng bạn có thể đem luộc cho miếng thịt vừa chín là vớt ra ngay, sau đó mới đem ướp gia vị nhé.\r\n\r\nCho thịt heo vào lò vi sóng\r\n\r\nBước 7: Xăm da heo\r\n\r\n- Miếng thịt sau khi đã làm chín phần da thì lấy ra và dùng 1 bó cây xiên thịt để xăm, bạn cứ xăm thật nhiều lần lên phần da, như vậy khi nướng da heo sẽ nổ giòn rụm mà không bị giòn cứng.\r\n\r\nDùng 1 bó cây xiên để xăm thịt heo\r\n\r\nBước 8: Pha hỗn hợp phết lên da heo\r\n\r\n- Tiếp theo bạn cho 1 thìa rượu vào bát con, thêm 1 thìa cà phê muối, 1 thìa giấm và khuấy đều cho muối tan hết. Sau đó dùng cọ phết đều nước giấm lên phần da heo cho đều, bạn phết 2 lần hoặc phết cho hết hỗn hợp nước giấm là được, để khoảng 10 phút sau đó dùng giấy thấm khô phần da.\nBước 9: Làm heo quay bằng nồi chiên không dầu\nLấy từng miếng thịt cho vào nồi chiên không dầu và mở ở chế độ nhiệt độ 180ºC để trong 45 phút. Sau 45 phút, các bạn lấy thịt ra và quét lên phần da một lớp dầu ăn mỏng. Sau đó tiếp tục cho vào nồi chiên ở nhiệt độ 200ºC thêm 10 phút nữa là được.', '', 5),
(20, 'Gà quay nguyên con', 'uploads/ga_quay_nguyen_con.jpg', '', 'Thịt gà dễ ăn, dễ chế biến, mùi vị hấp dẫn, giàu dinh dưỡng và là thực phẩm xuất hiện thường xuyên trong thực đơn hàng ngày của các gia đình. VinID bật mí cho cả nhà một công thức làm gà quay nguyên con siêu đơn giản, lại thơm ngon khó cưỡng để mọi người bổ sung vào bữa cơm gia đình nhé. ', '+) Nguyên liệu:\n\nGà nguyên con: 2 – 2.5kg\nMật ong: 6 muỗng canh \nXì dầu: 3 muỗng canh (có thể thay thế bằng nước tương)\nTương ớt: 2 muỗng canh \nNước mắm: 1 muỗng canh \nHạt nêm: 1 muỗng canh \nĐường: 1 muỗng canh \nỚt bột: 2 thìa cà phê (1 muỗng canh nếu ăn cay)\nMuối ăn: 2 thìa cà phê\nHành tím, tỏi\nDụng cụ: thau, dao, thớt, chén, chảo, lò nướng, bao tay, màngbọc thực phẩm, cọ quét.\n\n+) Cách làm:\n\nB1: Sơ chế gà\nGà mua về lấy nội tạng để riêng, món gà quay không dùng phần nội tạng. \nRửa sơ gà với nước sạch, cho một ít muối hạt lên tay, chà sát phần muối lên toàn bộ thân gà để làm sạch lông còn sót và khử mùi. \nXả lại nhiều lần với nước để khử mặn của muối hạt. \nĐể gà ráo nước hoặc dùng khăn thấm cho thật khô.\nLấy dao rạch vài đường nhỏ vào những chỗ thịt dày ở đùi, ức.\nB2: Ướp gà\nGiã nhuyễn tỏi và hành rồi trộn đều với các nguyên liệu còn lại: mật ong, xì dầu, tương ớt, nước mắm, hạt nêm, đường, muối ăn, ớt bột, nêm nếm lại cho vừa ăn. \nLấy phần nước sốt chà lên toàn thân gà bên ngoài, và bụng gà bên trong.\nMát xa, xoa đều hỗn hợp trên thân gà trong 5 – 10 phút cho gia vị được trải đều và ngấm vào thịt nhanh. \nDùng màng bọc thực phẩm bọc kín gà lại, cho vào ngăn mát tủ lạnh ít nhất 6 tiếng\nB3: Quay gà\nGà trước khi cho vào lò quay, để nguội ở ngoài trong 15 phút. \r\nBật lò nướng nhiệt độ 200 độ C, chế độ 2 lửa trước 15 phút để lò nóng đều. \nCho gà vào lò quay trong 10 phút ở 200 độ C thì lấy ra quét nước sốt ướp gà lên toàn bộ gà. \nLặp lại bước này 3 lần, sau lần quét cuối thì nướng tiếp 15 phút nữa. \nKiểm tra gà chín bằng cách dùng đũa xiên vào phần thịt dày ở đùi, nếu có nước chảy ra là gà chưa chín, lặp lại bước trên 1 lần nữa và kiểm tra lại. ', '', 5),
(21, 'Mực chiên giòn', 'uploads/muc_chien_gion.jpg', '', 'Mực chiên giòn đúng chuẩn sẽ có lớp vỏ bên ngoài giòn rụm, vàng ươm, phần mực bên trong thơm ngọt, thấm vị đậm đà mà không hề bị khô. Với món này mà ăn kèm với tương ớt hay sốt mayonnaise là tuyệt cú mèo cho mà xem.', 'Đang cập nhật', '', 6),
(22, 'Bánh tép chiên bột', 'uploads/banh_tep_chien_bot.jpg', '', 'Bánh tép chiên bột là một món ăn dân dã với cách làm đơn giản nhanh chóng nhưng hương vị hấp dẫn, lưu luyến mãi không thôi.', 'Đang cập nhật', '', 6),
(23, 'Tôm chiên bột', 'uploads/tom_chien_bot.webp', '', 'Tôm sú chiên xù là món ăn thường thấy trong các nhà hàng, bữa tiệc. Bởi không chỉ ngon miệng, đây còn là một món ăn đẹp mắt, kích thích mọi giác quan của người thưởng thức. Từng con tôm đỏ au, thơm ngọt, chắc thịt được tẩm trong lớp bột chiên giòn vàng ươm, thơm nức, cho ra đời món ăn hấp dẫn, hứa hẹn sẽ khiến các bé ở nhà mê tít cho xem', 'Đang cập nhật', '', 6),
(24, 'Cá chiên giòn', 'uploads/ca_chien_gion.jpg', '', 'Cá chiên giòn là món ăn thơm ngon, bổ dưỡng rất được trẻ nhỏ yêu thích bởi độ thơm, giòn, béo ngậy.', 'Đang cập nhật', '', 6),
(25, 'Bò kho', 'uploads/bo_kho.jpg', '', 'Cách nấu bò kho bánh mì ngon nhất đúng kiểu miền Trung', 'Đang cập nhật', '', 7),
(26, 'Cá kho', 'uploads/ca_kho.jpg', '', 'Cách kho cá ngon với chuối xanh và thịt ba chỉ nhanh gọn, đậm vị cùng với vị cay của ớt', 'Đang cập nhật', '', 7),
(27, 'Thịt kho nước dừa ', 'uploads/thit_kho_nuoc_dua.jpg', '', 'Cách làm thịt kho nước dừa ngày Tết lên màu cực đẹp, ngon mềm đưa cơm', 'Đang cập nhật', '', 7),
(28, 'Nộm gà hoa chuối', 'uploads/nom_ga_hoa_chuoi.jpg', '', 'Món ăn dân dã này vừa tận dụng thịt gà luộc còn dư, vừa giúp giải ngấy và kích thích vị giác trong mâm cỗ ngày Tết.', 'Đang cập nhật', '', 8),
(29, 'Nộm sứa su hào', 'uploads/nom_sua_xu_hao.jpg', '', 'Là một quốc gia gần biển, có lẽ đó mà biển vốn ưu ái cho nước ta về nhiều mặt, không chỉ là nơi vận chuyển hàng hóa,biển mang lại cho nước ta một khí hậu ôn hòa, mát mẻ mà còn là nơi cung cấp nguồn hải sản vô cùng phong phú như Tôm, cua , cá, ngao sò….trong đó đặc biệt là Sứa. Và nộm sứa trở thành một món ăn ưa thích không chỉ trong các bữa tiệc mà còn trong chính bữa cơm gia đình. Hôm nay mình sẽ giới thiệu các bạn món Nộm sứa su hào, vị thanh mát của su hào hòa lẫn với vị ngọt và giòn từ sứa sẽ là một sự kết hợp mang lại cho bạn cảm giác kích thích vị giác đến bất ngờ đấy nhé', 'Đang cập nhật', '', 8),
(30, 'Nộm rau muống thịt bò', 'uploads/nom_rau_muong_thit_bo.jpg', '', 'Trong danh sách những món nộm ngon của chúng ta không thể thiếu món nộm bò rau muống. Thịt bò mềm thơm, rau muống tươi giòn, kết hợp cùng nước chấm chua ngọt đậm đà khiến bữa cơm gia đình thêm phần ngon miệng. Hơn nữa món ăn này lại cân bằng dinh dưỡng và rất dễ dàng chế biến tại nhà nữa. Mời các bạn cùng tìm hiểu công thức ngay sau đây nhé!', 'Đang cập nhật', '', 8),
(31, 'Nộm dưa chuột chua ngọt', 'uploads/nom_dua_chuot_chua_ngot.jpg', '', 'Chỉ với vài nguyên liệu dễ kiếm là bạn đã có ngay món nộm dưa chuột giòn ngọt, thanh mát rất dễ ăn, cuốn hút người ăn từ khứu giác, thị giác đến thính giác, giúp bạn và gia đình có một bữa cơm ngon miệng.', 'Đang cập nhật', '', 8),
(32, 'Cá hồi áp chảo', 'uploads/ca_hoi_ap_chao.jpg', '', 'Món ăn có hương vị tuyệt đỉnh: Cá hồi áp chảo giòn bên ngoài và mềm bên trong, kết hợp vị béo ngọt của cá hồi tươi cùng vị chua ngọt thơm lừng của nước sốt bơ chanh, là một món ăn thơm ngon lại dễ làm bạn có thể \"tự thưởng\" sau một tuần làm việc chăm chỉ.', 'Đang cập nhật', '', 9),
(33, 'Ức gà áp chảo', 'uploads/uc_ga_ap_chao.jpg', '', 'Ức gà áp chảo giòn ngon, mềm mại và thơm lừng kết hợp với vị beo béo của sốt Bechamel kích thích đến từng giác quan trên cơ thể khi thưởng thức. Một món áp chảo đơn giản bạn có thể tự tay làm ngay cho những người thân yêu.', 'Đang cập nhật', '', 9),
(34, 'Bò áp chảo', 'uploads/bo_ap_chao.jpg', '', 'Thịt bò thơm ngon, hấp dẫn cùng hương vị đậm đà của nước sốt từ dưa chuột muối và rượu vang đỏ, nhất định sẽ khiến cả gia đình bạn phải “trầm trồ” khi thưởng thức món áp chảo \"ngon như nhà hàng\" này đấy!', '+) Nguyên liệu:\n\n300g Thịt bò\n2 nhánh tỏi\n1 cây sả tươi\n1 nhánh gừng\n2 nhánh cây hương thảo2 muỗng cà phê dầu hào\n1 muỗng canh dầu oliu\n1 ít muối/ tiêu xay\n\n+) Cách làm:\n1. Sơ chế nguyên liệu\nThịt thăn bò sau khi mua về chỉ cần rửa sạch, sau đó cắt thành từng miếng dày khoảng bằng 1 lóng rưỡi ngón tay rồi lấy 1 cái khăn giấy thấm ráo nước cho miếng thịt thật khô.\nRửa sạch 2 nhánh tỏi, 1 cây sả tươi và 1 nhánh gừng nhỏ rồi bóc vỏ tỏi, cạo sạch vỏ gừng. Sau đó, đập dập và băm nhuyễn tất cả số tỏi, sả tươi, gừng đó.\n2. Ướp thịt thăn bò\nCho miếng thịt bò vào một cái tô hoặc khay sau đó ướp thịt với hỗn hợp gia vị gồm: 2 muỗng cà phê dầu hào, 2 muỗng cà phê bột ngũ vị hương, 1 ít muối, 1 ít tiêu xay và hỗn hợp tỏi, sả tươi, gừng băm nhuyễn.\nĐeo bao tay nilon vào bóp nhẹ thịt để gia vị áo đều thịt rồi cho vào ngăn mát tủ lạnh ướp khoảng 30 phút để thịt bò thấm đều gia vị.\n3.Áp chảo thịt thăn bò\nBắc chảo lên bếp rồi làm nóng 1 muỗng canh dầu oliu với lửa nhẹ. Sau khoảng 3 phút thì cho thịt bò vào áp chảo mỗi mặt khoảng 6 phút, cho 2 nhánh hương thảo đã rửa sạch lên bề mặt để thịt dậy mùi thơm.\nKhi thịt bò đã chín đều 2 mặt thì dùng kéo cắt thịt thành từng miếng vừa ăn rồi bày ra đĩa, rắc thêm một ít tiêu xay và thưởng thức.\n4. Hoàn thành', '', 9),
(35, 'Vịt áp chảo', 'uploads/vit_ap_chao.jpg', '', 'Ức vịt áp chảo có phần da mỏng và phần thịt đầy đặn, vừa béo lại vừa mềm, kết hợp với vị chua ngọt của nước sốt cam, một món ăn thơm lừng và là món khai vị số một đấy!', '+) Nguyên liệu:\n\nBao gồm: 1 con vịt; 5 muỗng dầu hào;100 ml rượu trắng; 3 muỗng đường; 2 muỗng mật ong; 1 muỗng dầu mè; 1 gói ngũ vị hương; hoa hồi, thảo quả, quế mỗi thứ một chút; gừng, tỏi, gia vị cơ bản như dầu ăn, muối...\n\n+) Cách làm:\n\nVịt mua loại làm sẵn ở chợ hoặc có thời gian mua vịt sống về làm. Cần phải sơ chế khử mùi hôi của vịt với giấm, chanh, gừng...sau đó để ráo.\nĐập dập tỏi, gừng; thảo quả, quế, hồi đem giũ cho hết bụi rồi rang lên đến khi có mùi thơm. \nCho vịt vào nồi cũng các nguyên liệu phụ vừa sơ chế và một ít gia vị như muối, đường, bột nêm rồi luộc vịt.\n\r\nKhi vịt chín vớt vịt ra để thật ráo nước rồi chặt đôi vịt ra hoặc có thể để nguyên.\nƯớp ngũ vị hương với vịt đã luộc khoảng 15 phút.\nChọn một chiếc chảo sâu lòng và đổ ngập dầu ăn, khi dầu sôi già thì cho vịt ướp ngũ vị hương vào chiên. Cách làm món vịt quay áp chảo giòn mà vẫn không bị khô là nên đun lửa lớn.\nLàm nước sốt bao gồm các nguyên liệu còn lại đã chuẩn bị đánh cho tan hết vào nhau.\ntry {\r\n                            Bitmap b = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);\r\n                            imageViewAdd.setImageBitmap(b);\r\n                            imgAdd=b;\r\n                        } catch (FileNotFoundException e) {\r\n                            e.printStackTrace();\r\n                        } catch (IOException e) {\r\n                            e.printStackTrace();\r\n                        }', '', 9);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoidung`
--

CREATE TABLE `nguoidung` (
  `id` int(11) NOT NULL,
  `tendaydu` varchar(50) NOT NULL,
  `anhdaidien` text NOT NULL,
  `ngaysinh` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `gioitinh` int(1) NOT NULL,
  `sdt` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nguoidung`
--

INSERT INTO `nguoidung` (`id`, `tendaydu`, `anhdaidien`, `ngaysinh`, `email`, `gioitinh`, `sdt`) VALUES
(1, 'Phạm Đình Thắng ', 'uploads/1210692373_1655304488.jpeg', '2022-05-21', 'thangyb2706@gmail.com', 0, '0835787380');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quest`
--

CREATE TABLE `quest` (
  `keyQuest` int(11) NOT NULL,
  `account` varchar(50) NOT NULL,
  `quest` text NOT NULL,
  `answer` text DEFAULT NULL,
  `timeQ` varchar(20) NOT NULL,
  `checkQ` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `report`
--

CREATE TABLE `report` (
  `keyRP` int(11) NOT NULL,
  `titleRP` varchar(100) NOT NULL,
  `descRP` text NOT NULL,
  `imgRP` text DEFAULT NULL,
  `timeRP` varchar(20) NOT NULL,
  `fixRP` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `review`
--

CREATE TABLE `review` (
  `keyRV` int(11) NOT NULL,
  `account` varchar(50) NOT NULL,
  `keyFood` int(11) NOT NULL,
  `content` text NOT NULL,
  `timeRV` varchar(20) NOT NULL,
  `img` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `review`
--

INSERT INTO `review` (`keyRV`, `account`, `keyFood`, `content`, `timeRV`, `img`) VALUES
(1, 'TEST', 35, 'XXX', '15/06/2022 23:23:31', ''),
(2, 'thangyb27', 35, 'Test', '15/06/2022 23:37:07', ''),
(3, 'thangyb27', 35, 'Tesst', '15/06/2022 23:39:51', ''),
(4, 'thangyb27', 35, 'x', '15/06/2022 23:43:37', ''),
(5, 'thangyb27', 35, 'Test', '15/06/2022 23:47:19', ''),
(7, 'thangyb27', 35, 'DM thang ninh', '16/06/2022 00:21:17', '16553136781655313677804.png'),
(8, 'thangyb27', 35, 'Rén rồi thì nói đi cưng', '16/06/2022 00:22:49', '16553137691655313769570.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `tentaikhoan` varchar(50) NOT NULL,
  `matkhau` varchar(50) NOT NULL,
  `idnguoidung` int(11) NOT NULL,
  `quyen` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`tentaikhoan`, `matkhau`, `idnguoidung`, `quyen`) VALUES
('thangyb27', 'thangyb27', 1, 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cauhoi`
--
ALTER TABLE `cauhoi`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaimonan`
--
ALTER TABLE `loaimonan`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `monan`
--
ALTER TABLE `monan`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `quest`
--
ALTER TABLE `quest`
  ADD PRIMARY KEY (`keyQuest`);

--
-- Chỉ mục cho bảng `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`keyRP`);

--
-- Chỉ mục cho bảng `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`keyRV`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cauhoi`
--
ALTER TABLE `cauhoi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `loaimonan`
--
ALTER TABLE `loaimonan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `monan`
--
ALTER TABLE `monan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `quest`
--
ALTER TABLE `quest`
  MODIFY `keyQuest` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `report`
--
ALTER TABLE `report`
  MODIFY `keyRP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `review`
--
ALTER TABLE `review`
  MODIFY `keyRV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
