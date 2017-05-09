SET SERVEROUTPUT ON;
DECLARE
    ida idAssembling;
    seq varchar2(4);
    e ThuChi_objtyp;
BEGIN
    ida := idAssembling('', 0);
    seq := '';
    e := ThuChi_objtyp(
        '201708060102',
        CURRENT_DATE,
        'SOME NOTE',
        0,
        123,
        ChiTietThuChi_ntabtyp(
            ChitietThuChi_objtyp(
                '201501231829',
                'H',
                123,
                'some inner note'
            )
        )
    );
    
    SELECT SUBSTR(id, 0, 8), TO_NUMBER(SUBSTR(id, 9, 4))
    INTO ida.prefix, ida.last_seq
    FROM (
        SELECT MaThuChi id
        FROM (
            SELECT MaThuChi
            FROM ThuChi
            UNION ALL
            SELECT TO_CHAR(CURRENT_DATE, 'YYYYMMDD')||'0000' MaThuChi
            FROM Dual
        )
        ORDER BY id DESC
    )
    WHERE ROWNUM = 1;

    ida.last_seq := 1 + ida.last_seq;
    IF (ida.last_seq < 1000 AND ida.last_seq >= 100) THEN
        seq := '0' || TO_CHAR(ida.last_seq);
    ELSIF (ida.last_seq < 100 AND ida.last_seq >= 10) THEN
        seq := '00' || TO_CHAR(ida.last_seq);
    ELSIF (ida.last_seq < 10) THEN
        seq := '000' || TO_CHAR(ida.last_seq);
    END IF;

    e.MaThuChi := ida.prefix || seq;
    INSERT INTO ThuChi VALUES (e);
END;
/
