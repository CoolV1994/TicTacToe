<?php
require_once 'config.php';

/**
 *
 * @author Vinnie
 */
function checkPortOpen($ip, $port) {
	$request = 'http://ports.yougetsignal.com/check-port.php';
	$parameters = 'remoteAddress=' . $ip . '&portNumber=' . $port;

	$ch = curl_init($request);
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $parameters);
	curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
	curl_setopt($ch, CURLOPT_HEADER, 0);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);

	$response = curl_exec($ch);
	if (strpos($response, 'open') !== false) {
		return true;
	} else {
		return false;
	}
}

if (!$_POST['port']) {
	die();
}

if (!$_POST['name']) {
	die();
}

if (!checkPortOpen($_SERVER['REMOTE_ADDR'], $_POST['port'])) {
	echo "Closed";
	die();
}

try {
    $conn = new PDO('mysql:host='.DB_HOST.';dbname='.DB_NAME, DB_USER, DB_PASS);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	$sql = "SELECT id FROM servers WHERE ip=:ip AND port=:port";
	$stmt = $conn->prepare($sql);
	$stmt->bindValue(':ip', $_SERVER['REMOTE_ADDR'], PDO::PARAM_STR);
	$stmt->bindValue(':port', $_POST['port'], PDO::PARAM_INT);
	$stmt->execute();
	$serverID = $stmt->fetchObject()->id;

	if ($serverID > 0) {
		$sql = "UPDATE servers SET name=:name, date=NOW() WHERE id=:id";
		$stmt = $conn->prepare($sql);
		$stmt->bindValue(':name', $_POST['name'], PDO::PARAM_STR);
		$stmt->bindValue(':id', $serverID, PDO::PARAM_INT);
		$stmt->execute();
	} else {
		$sql = "INSERT INTO servers (name, ip, port) VALUES (:name, :ip, :port)";
		$stmt = $conn->prepare($sql);
		$stmt->bindValue(':name', $_POST['name'], PDO::PARAM_STR);
		$stmt->bindValue(':ip', $_SERVER['REMOTE_ADDR'], PDO::PARAM_STR);
		$stmt->bindValue(':port', $_POST['port'], PDO::PARAM_INT);
		$stmt->execute();
		$serverID = $conn->lastInsertId();
	}
} catch(PDOException $e) {
    echo "Error: " . $e->getMessage();
}

echo "ID\n" . $serverID;
