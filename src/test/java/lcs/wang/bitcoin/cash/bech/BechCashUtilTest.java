package lcs.wang.bitcoin.cash.bech;

import static org.junit.Assert.assertEquals;

import java.util.Map.Entry;

import org.junit.Test;

import lcs.wang.bitcoin.cash.bech.BechCashUtil;

public class BechCashUtilTest {

    BechCashUtil CASH = BechCashUtil.getInstance();

    @Test
    public void testEncode() {
        String code = CASH.encode("prefix", new byte[] {});
        assertEquals("prefix:x64nx6hz", code);
        code = CASH.encode("p", new byte[] {});
        assertEquals("p:gpf8m4h7", code);
        byte[] res = CASH.bchDecode("qpzry9x8gf2tvdw0s3jn54khce6mua7l");
        code = CASH.encode("bitcoincash", res);
        assertEquals("bitcoincash:qpzry9x8gf2tvdw0s3jn54khce6mua7lcw20ayyn", code);
        res = CASH.bchDecode("555555555555555555555555555555555555555555555");
        code = CASH.encode("bchreg", res);
        assertEquals("bchreg:555555555555555555555555555555555555555555555udxmlmrz", code);
    }

    @Test
    public void testDecode() {
        String org = "bitcoincash:qpm2qsznhks23z7629mms6s4cwef74vcwvy22gdx6a";
        Entry<String, byte[]> entry = CASH.decode(org, "");
        System.out.println(entry.getKey());
        // System.out.println(entry.getValue());
        String encode = CASH.encode("bitcoincash", entry.getValue());
        assertEquals(org, encode);
    }

    @Test
    public void testTransitionBchToBech32() {
        {
            // 1BpEi6DfDAUFd7GtittLSdBeYJvcoaVggu bitcoincash:qpm2qsznhks23z7629mms6s4cwef74vcwvy22gdx6a
            byte[] code = CASH.transitionBchToBech32("1BpEi6DfDAUFd7GtittLSdBeYJvcoaVggu");
            String addr = CASH.encode("bitcoincash", code);
            assertEquals("bitcoincash:qpm2qsznhks23z7629mms6s4cwef74vcwvy22gdx6a", addr);
        }
        {
            byte[] code = CASH.transitionBchToBech32("1KXrWXciRDZUpQwQmuM1DbwsKDLYAYsVLR");
            String addr = CASH.encode("bitcoincash", code);
            assertEquals("bitcoincash:qr95sy3j9xwd2ap32xkykttr4cvcu7as4y0qverfuy", addr);
        }

        {
            // 16w1D5WRVKJuZUsSRzdLp9w3YGcgoxDXb bitcoincash:qqq3728yw0y47sqn6l2na30mcw6zm78dzqre909m2r
            byte[] code = CASH.transitionBchToBech32("16w1D5WRVKJuZUsSRzdLp9w3YGcgoxDXb");
            String addr = CASH.encode("bitcoincash", code);
            assertEquals("bitcoincash:qqq3728yw0y47sqn6l2na30mcw6zm78dzqre909m2r", addr);
        }
        {
            // 3CWFddi6m4ndiGyKqzYvsFYagqDLPVMTzC bitcoincash:ppm2qsznhks23z7629mms6s4cwef74vcwvn0h829pq
            byte[] code = CASH.transitionBchToBech32("3CWFddi6m4ndiGyKqzYvsFYagqDLPVMTzC");
            String addr = CASH.encode("bitcoincash", code);
            assertEquals("bitcoincash:ppm2qsznhks23z7629mms6s4cwef74vcwvn0h829pq", addr);
        }
        {
            // 3LDsS579y7sruadqu11beEJoTjdFiFCdX4 bitcoincash:pr95sy3j9xwd2ap32xkykttr4cvcu7as4yc93ky28e
            byte[] code = CASH.transitionBchToBech32("3LDsS579y7sruadqu11beEJoTjdFiFCdX4");
            String addr = CASH.encode("bitcoincash", code);
            assertEquals("bitcoincash:pr95sy3j9xwd2ap32xkykttr4cvcu7as4yc93ky28e", addr);
        }
        {
            // 31nwvkZwyPdgzjBJZXfDmSWsC4ZLKpYyUw bitcoincash:pqq3728yw0y47sqn6l2na30mcw6zm78dzq5ucqzc37
            byte[] code = CASH.transitionBchToBech32("31nwvkZwyPdgzjBJZXfDmSWsC4ZLKpYyUw");
            String addr = CASH.encode("bitcoincash", code);
            assertEquals("bitcoincash:pqq3728yw0y47sqn6l2na30mcw6zm78dzq5ucqzc37", addr);
        }
    }

    @Test
    public void bit() {
        System.out.println(Integer.toBinaryString(-20));
        for (int i = 0; i < 8; i++) {
            int bit = CASH.getBitAt(new byte[] { -20 }, i);
            char b = Integer.toBinaryString(-20).charAt(24 + i);
            assertEquals("" + bit, "" + b);
        }
        System.out.println("0000" + Integer.toBinaryString(8));
        for (int i = 8; i < 16; i++) {
            int bit = CASH.getBitAt(new byte[] { -20, 8 }, i);
            char b = ("0000" + Integer.toBinaryString(8)).charAt(i % 8);
            assertEquals("" + bit, "" + b);
        }
    }

}
