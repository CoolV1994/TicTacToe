<?php
require_once 'config.php';

/**
 *
 * @author Vinnie
 */
if (!$_POST['id']) {
	die();
}

try {
    $conn = new PDO('mysql:host='.DB_HOST.';dbname='.DB_NAME, DB_USER, DB_PASS);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "SELECT ip, port FROM servers WHERE id=:id AND date IS NOT NULL ORDER BY date DESC LIMIT 0, 1";
	$stmt = $conn->prepare($sql);
	$stmt->bindValue(':id', $_POST['id'], PDO::PARAM_INT);
    $stmt->execute();
	$stmt->setFetchMode(PDO::FETCH_OBJ);
	while ($server = $stmt->fetch()) {
		echo "Server\n" . $server->ip . "\n" . $server->port;
	}
} catch(PDOException $e) {
    echo "Error: " . $e->getMessage();
}
